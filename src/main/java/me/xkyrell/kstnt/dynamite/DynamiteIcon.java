package me.xkyrell.kstnt.dynamite;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public interface DynamiteIcon {

    Component getDisplayName();

    List<Component> getLore();

    boolean isGlowing();

    @NonNull
    ItemStack compose();

}
