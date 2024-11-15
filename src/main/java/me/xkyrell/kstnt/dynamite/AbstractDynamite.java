package me.xkyrell.kstnt.dynamite;

import lombok.*;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.logic.responsible.EntityMatchResponsible;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.List;

import static me.xkyrell.kstnt.TNTConstants.DATA_KEY;

@Getter
@RequiredArgsConstructor
public abstract class AbstractDynamite implements Dynamite, EntityMatchResponsible {

    private final String name;
    private final DynamiteIcon icon;
    private final List<Attribute<?>> attributes;

    protected boolean hasKeyOrDefault(PersistentDataContainer container, NamespacedKey key) {
        NamespacedKey effectiveKey = (key != null) ? key : DATA_KEY;
        String name = container.get(effectiveKey, PersistentDataType.STRING);
        return getName().equals(name);
    }

    @Override
    public final boolean matchesEntity(Entity entity) {
        return hasKeyOrDefault(entity.getPersistentDataContainer(), null);
    }
}
