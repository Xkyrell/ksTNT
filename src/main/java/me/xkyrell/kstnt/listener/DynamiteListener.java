package me.xkyrell.kstnt.listener;

import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.logic.responsible.BreakResponsible;
import me.xkyrell.kstnt.dynamite.logic.responsible.IgniteResponsible;
import me.xkyrell.kstnt.dynamite.logic.responsible.PlaceResponsible;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

@RequiredArgsConstructor
public class DynamiteListener implements Listener {

    private final DynamiteService dynamiteService;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        dynamiteService.getResolver().getDynamitesByLogic(BreakResponsible.class)
                .peek(resp -> resp.onBreak(event))
                .filter(resp -> resp.matchesBlock(event.getBlock()))
                .map(resp -> (Dynamite) resp)
                .filter(dynamite -> dynamite.getAttributes() != null)
                .forEach(dynamite -> { /* Handling attribute logic */ });
    }

    @EventHandler
    public void onIgnite(EntitySpawnEvent event) {
        dynamiteService.getResolver().getDynamitesByLogic(IgniteResponsible.class)
                .peek(resp -> resp.onIgnite(event))
                .filter(resp -> resp.matchesEntity(event.getEntity()))
                .map(resp -> (Dynamite) resp)
                .filter(dynamite -> dynamite.getAttributes() != null)
                .forEach(dynamite -> { /* Handling attribute logic */ });
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        dynamiteService.getResolver().getDynamitesByLogic(PlaceResponsible.class)
                .peek(resp -> resp.onPlace(event))
                .filter(resp -> resp.matchesBlock(event.getBlock()))
                .map(resp -> (Dynamite) resp)
                .filter(dynamite -> dynamite.getAttributes() != null)
                .forEach(dynamite -> { /* Handling attribute logic */ });
    }
}
