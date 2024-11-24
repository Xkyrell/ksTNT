package me.xkyrell.kstnt.dynamite.attribute.impl;

import me.xkyrell.kstnt.dynamite.attribute.AbstractAttribute;
import me.xkyrell.kstnt.dynamite.logic.responsible.PreExplosionResponsible;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import java.util.function.Consumer;

public class AftermathAttribute extends AbstractAttribute<String> implements PreExplosionResponsible {

    public AftermathAttribute(String value) {
        super("aftermath", value);
    }

    @Override
    public void onPreExplosion(ExplosionPrimeEvent event) {
        try {
            String uppedValue = getValue().toUpperCase();
            Type.valueOf(uppedValue).accept(event);
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid saturation type provided.", ex);
        }
    }

    private enum Type implements Consumer<ExplosionPrimeEvent> {

        FIRE_AREA {
            private static final int FIRE_BURN_DURATION = 160;

            @Override
            public void accept(ExplosionPrimeEvent event) {
                Location location = event.getEntity().getLocation();
                location.getNearbyEntitiesByType(
                        LivingEntity.class, event.getRadius()
                ).forEach(entity -> {
                    entity.setFireTicks(FIRE_BURN_DURATION);
                });

                FIRE.accept(event);
            }
        },

        FIRE {
            @Override
            public void accept(ExplosionPrimeEvent event) {
                event.setFire(true);
            }
        },
    }
}
