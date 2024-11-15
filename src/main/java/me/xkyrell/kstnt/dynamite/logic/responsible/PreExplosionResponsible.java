package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public interface PreExplosionResponsible extends DynamiteLogic {

    void onPreExplosion(ExplosionPrimeEvent event);

}
