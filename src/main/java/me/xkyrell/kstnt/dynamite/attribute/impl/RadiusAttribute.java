package me.xkyrell.kstnt.dynamite.attribute.impl;

import me.xkyrell.kstnt.dynamite.attribute.AbstractAttribute;
import me.xkyrell.kstnt.dynamite.logic.responsible.PreExplosionResponsible;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class RadiusAttribute extends AbstractAttribute<Double> implements PreExplosionResponsible {

    public RadiusAttribute(Double value) {
        super("radius", value);
    }

    @Override
    public void onPreExplosion(ExplosionPrimeEvent event) {
        event.setRadius(getValue().floatValue());
    }
}
