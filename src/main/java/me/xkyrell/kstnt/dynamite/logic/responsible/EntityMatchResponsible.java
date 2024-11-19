package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.entity.Entity;

public interface EntityMatchResponsible extends DynamiteLogic {

    boolean matchesEntity(Entity entity);

}
