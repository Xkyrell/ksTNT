package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.event.block.BlockBreakEvent;

public interface BreakResponsible extends DynamiteLogic, BlockMatchResponsible {

    void onBreak(BlockBreakEvent event);

}
