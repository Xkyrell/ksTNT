package me.xkyrell.kstnt.config;

import lombok.NonNull;
import me.xkyrell.kstnt.TNTConstants;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.DynamiteIcon;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.attribute.resolver.AttributeResolver;
import me.xkyrell.kstnt.dynamite.impl.SimpleDynamite;
import me.xkyrell.kstnt.dynamite.impl.SimpleDynamiteIcon;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import me.xkyrell.kstnt.util.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneralConfig extends Config {

    private final DynamiteService dynamiteService;
    private final AttributeResolver attributeResolver;

    public GeneralConfig(Plugin plugin, DynamiteService dynamiteService, AttributeResolver attributeResolver) {
        super(plugin, "config");

        this.dynamiteService = dynamiteService;
        this.attributeResolver = attributeResolver;
        updateOrLoad();
    }

    @Override
    public void reload() {
        super.reload();
        updateOrLoad();
    }

    private void updateOrLoad() {
        ConfigurationSection dynamitesSection = getSource().getConfigurationSection("dynamites");
        if (dynamitesSection == null) {
            return;
        }

        for (String key : dynamitesSection.getKeys(false)) {
            ConfigurationSection dynamiteSection = dynamitesSection.getConfigurationSection(key);
            if (dynamiteSection == null) {
                continue;
            }

            Optional<Dynamite> dynamiteOptional = dynamiteService.getResolver().resolve(key);
            if (dynamiteOptional.isPresent()) {
                dynamiteService.unregister(key);
            }

            DynamiteIcon icon = loadIcon(dynamiteSection.getConfigurationSection("icon"), key);
            List<Attribute<?>> attributes = loadAttributes(dynamiteSection.getConfigurationSection("attributes"));

            Dynamite dynamite = new SimpleDynamite(key, icon, attributes);
            dynamiteService.register(key, dynamite);
        }
    }

    private DynamiteIcon loadIcon(@NonNull ConfigurationSection section, String key) {
        Component displayName = Colors.fromLegacy(section.getString("name"));
        List<Component> lore = Colors.fromLegacy(section.getStringList("lore"));

        boolean glowing = section.getBoolean("glowing");

        return new SimpleDynamiteIcon(displayName, lore, glowing) {
            @Override
            protected ItemStack prepareItemStack() {
                ItemStack item = super.prepareItemStack();
                item.editMeta(meta -> {
                    PersistentDataContainer container = meta.getPersistentDataContainer();
                    container.set(TNTConstants.DATA_KEY, PersistentDataType.STRING, key);
                });
                return item;
            }
        };
    }

    private List<Attribute<?>> loadAttributes(@NonNull ConfigurationSection section) {
        List<Attribute<?>> attributes = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            Object value = section.get(key);
            loadAttribute(key, value).ifPresent(attributes::add);
        }
        return attributes;
    }

    @SuppressWarnings("unchecked")
    private Optional<Attribute<?>> loadAttribute(String key, Object value) {
        return attributeResolver.resolve(key).map(attribute -> {
            ((Attribute<Object>) attribute).setValue(value);
            return attribute;
        });
    }
}
