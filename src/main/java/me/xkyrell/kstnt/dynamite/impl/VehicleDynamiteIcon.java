package me.xkyrell.kstnt.dynamite.impl;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class VehicleDynamiteIcon extends SimpleDynamiteIcon {

    public VehicleDynamiteIcon(Component displayName, List<Component> lore, boolean glowing) {
        super(displayName, lore, glowing);
    }

    @Override
    protected ItemStack prepareItemStack() {
        ItemStack internal = super.prepareItemStack();
        internal.setType(Material.TNT_MINECART);
        return internal;
    }
}
