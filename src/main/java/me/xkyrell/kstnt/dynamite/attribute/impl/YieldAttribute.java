package me.xkyrell.kstnt.dynamite.attribute.impl;

import me.xkyrell.kstnt.dynamite.attribute.AbstractAttribute;
import me.xkyrell.kstnt.dynamite.logic.responsible.ExplosionResponsible;
import org.bukkit.event.entity.EntityExplodeEvent;

public class YieldAttribute extends AbstractAttribute<Double> implements ExplosionResponsible {

    public YieldAttribute(Double value) {
        super("yield", value);
    }

    @Override
    public void onExplosion(EntityExplodeEvent event) {
        event.setYield(getValue().floatValue());
    }
}
