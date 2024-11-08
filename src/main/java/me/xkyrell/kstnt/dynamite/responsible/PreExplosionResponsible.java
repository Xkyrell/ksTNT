package me.xkyrell.kstnt.dynamite.responsible;

import org.bukkit.event.entity.ExplosionPrimeEvent;

public interface PreExplosionResponsible {

    void onPreExplosion(ExplosionPrimeEvent event);

}
