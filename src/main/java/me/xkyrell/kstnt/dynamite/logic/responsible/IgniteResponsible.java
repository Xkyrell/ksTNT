package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.event.entity.EntitySpawnEvent;

public interface IgniteResponsible extends DynamiteLogic {

    void onIgnite(EntitySpawnEvent event);

}
