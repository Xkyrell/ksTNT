package me.xkyrell.kstnt.dynamite.logic.responsible;

import org.bukkit.event.block.BlockBreakEvent;

public interface BreakResponsible extends BlockMatchResponsible {

    void onBreak(BlockBreakEvent event);

}
