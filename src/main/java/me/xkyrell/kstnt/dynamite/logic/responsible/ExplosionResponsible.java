package me.xkyrell.kstnt.dynamite.logic.responsible;

import org.bukkit.event.entity.EntityExplodeEvent;

public interface ExplosionResponsible extends EntityMatchResponsible {

    void onExplosion(EntityExplodeEvent event);

}
