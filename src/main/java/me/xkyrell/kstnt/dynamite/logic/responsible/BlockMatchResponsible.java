package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.block.Block;

public interface BlockMatchResponsible extends DynamiteLogic {

    boolean matchesBlock(Block block);

}
