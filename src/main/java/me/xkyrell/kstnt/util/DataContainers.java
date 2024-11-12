package me.xkyrell.kstnt.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;

import static me.xkyrell.kstnt.TNTConstants.*;

@UtilityClass
public class DataContainers {

    public NamespacedKey getKeyByBlock(Block block) {
        Location location = block.getLocation();
        String locationKey = String.format("%s_%d_%d_%d",
                location.getWorld().getName(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        );

        String fullKey = KEY.concat("-").concat(locationKey);
        return new NamespacedKey(NAMESPACE, fullKey);
    }
}
