package me.xkyrell.kstnt.dynamite.attribute.impl;

import me.xkyrell.kstnt.dynamite.attribute.AbstractAttribute;
import me.xkyrell.kstnt.dynamite.logic.responsible.IgniteResponsible;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntitySpawnEvent;

public class FuseAttribute extends AbstractAttribute<Integer> implements IgniteResponsible {

    private static final int TICKS_PER_SECOND = 20;

    public FuseAttribute(Integer value) {
        super("fuse", value);
    }

    @Override
    public void onIgnite(EntitySpawnEvent event) {
        TNTPrimed tnt = (TNTPrimed) event.getEntity();
        tnt.setFuseTicks(getValue() * TICKS_PER_SECOND);
    }
}
