package me.xkyrell.kstnt.listener;

import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import me.xkyrell.kstnt.dynamite.logic.responsible.BreakResponsible;
import me.xkyrell.kstnt.dynamite.logic.responsible.IgniteResponsible;
import me.xkyrell.kstnt.dynamite.logic.responsible.PlaceResponsible;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@RequiredArgsConstructor
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

    private <T extends DynamiteLogic> void handleDynamite(Class<T> clazz, Predicate<T> matches, Consumer<T> handler) {
        dynamiteService.getResolver().getDynamitesByLogic(clazz)
                .peek(handler)
                .filter(matches)
                .map(resp -> (Dynamite) resp)
                .map(Dynamite::getAttributes)
                .filter(Objects::nonNull)
                .forEach(attributes -> handleAttribute(attributes, clazz, handler));
    }

    private <T extends DynamiteLogic> void handleAttribute(List<Attribute<?>> attributes, Class<T> clazz, Consumer<T> handler) {
        attributes.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .forEach(handler);
    }
}
