package me.xkyrell.kstnt.dynamite.logic.responsible;

import org.bukkit.event.entity.EntitySpawnEvent;

public interface IgniteResponsible extends EntityMatchResponsible {

    void onIgnite(EntitySpawnEvent event);

}
