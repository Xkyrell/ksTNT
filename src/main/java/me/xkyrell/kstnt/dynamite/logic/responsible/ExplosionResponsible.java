package me.xkyrell.kstnt.dynamite.logic.responsible;

import me.xkyrell.kstnt.dynamite.logic.DynamiteLogic;
import org.bukkit.event.entity.EntityExplodeEvent;

public interface ExplosionResponsible extends DynamiteLogic {

    void onExplosion(EntityExplodeEvent event);

}
