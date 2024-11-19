package me.xkyrell.kstnt.dynamite.logic.responsible;

import org.bukkit.event.block.BlockPlaceEvent;

public interface PlaceResponsible extends BlockMatchResponsible {

    void onPlace(BlockPlaceEvent event);

}
