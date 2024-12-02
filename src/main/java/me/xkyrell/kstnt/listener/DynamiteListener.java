package me.xkyrell.kstnt.listener;

import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import me.xkyrell.kstnt.dynamite.logic.responsible.*;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DynamiteListener implements Listener {

    private final DynamiteService dynamiteService;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        handleDynamite(BreakResponsible.class,
                resp -> resp.matchesBlock(event.getBlock()),
                resp -> resp.onBreak(event)
        );
    }

    @EventHandler
    public void onIgnite(EntitySpawnEvent event) {
        handleDynamite(IgniteResponsible.class,
                resp -> resp.matchesEntity(event.getEntity()),
                resp -> resp.onIgnite(event)
        );
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        handleDynamite(PlaceResponsible.class,
                resp -> resp.matchesBlock(event.getBlock()),
                resp -> resp.onPlace(event)
        );
    }

    @EventHandler
    public void onPreExplosion(ExplosionPrimeEvent event) {
        handleEntityDynamite(PreExplosionResponsible.class,
                event::getEntity, resp -> resp.onPreExplosion(event)
        );
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        handleEntityDynamite(ExplosionResponsible.class,
                event::getEntity, resp -> resp.onExplosion(event)
        );
    }

    private <T extends DynamiteLogic> void handleDynamite(
            Class<T> clazz, Predicate<T> matches, Consumer<T> handler
    ) {
        handleDynamite(clazz, clazz, matches, matches, handler, handler);
    }

    private <T extends EntityMatchResponsible> void handleEntityDynamite(
            Class<T> clazz, Supplier<Entity> entity, Consumer<T> handler
    ) {
        handleDynamite(EntityMatchResponsible.class, clazz,
                resp -> resp.matchesEntity(entity.get()),
                resp -> resp.matchesEntity(entity.get()),
                __ -> {}, handler
        );
    }

    private <T extends DynamiteLogic, A extends DynamiteLogic> void handleDynamite(
            Class<T> clazz, Class<A> attributeClazz,
            Predicate<T> matches, Predicate<A> attributeMatches,
            Consumer<T> handler, Consumer<A> attributeHandler
    ) {
        dynamiteService.getResolver().getDynamitesByLogic(clazz)
                .peek(handler)
                .filter(matches)
                .map(resp -> (Dynamite) resp)
                .map(Dynamite::getAttributes)
                .filter(Objects::nonNull)
                .forEach(attributes -> handleAttribute(
                        attributes, attributeClazz, attributeMatches, attributeHandler
                ));
    }

    private <T extends DynamiteLogic> void handleAttribute(
            List<Attribute<?>> attributes, Class<T> clazz,
            Predicate<T> matches, Consumer<T> handler
    ) {
        attributes.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .filter(matches)
                .forEach(handler);
    }
}
