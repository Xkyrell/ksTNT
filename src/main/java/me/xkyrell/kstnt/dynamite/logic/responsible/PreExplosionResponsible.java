package me.xkyrell.kstnt.dynamite.logic.responsible;

import org.bukkit.event.entity.ExplosionPrimeEvent;

public interface PreExplosionResponsible extends EntityMatchResponsible {

    void onPreExplosion(ExplosionPrimeEvent event);

}
