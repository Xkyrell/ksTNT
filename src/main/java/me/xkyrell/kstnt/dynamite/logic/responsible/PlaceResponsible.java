package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.event.block.BlockPlaceEvent;

public interface PlaceResponsible extends DynamiteLogic {

    void onPlace(BlockPlaceEvent event);

}
