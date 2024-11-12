package me.xkyrell.kstnt.dynamite.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.dynamite.DynamiteIcon;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SimpleDynamiteIcon implements DynamiteIcon {

    private final Component displayName;
    private final List<Component> lore;
    private final boolean glowing;

    private ItemStack internal;

    @NonNull
    @Override
    public final ItemStack compose() {
        if (internal == null) {
            internal = prepareItemStack();
        }
        return internal;
    }

    protected ItemStack prepareItemStack() {
        ItemStack internal = new ItemStack(Material.TNT);
        internal.editMeta(meta -> {
            meta.displayName(displayName);
            meta.lore(lore);

            if (glowing) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
        });
        return internal;
    }
}
