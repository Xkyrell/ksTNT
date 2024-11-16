package me.xkyrell.kstnt.dynamite.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xkyrell.kstnt.dynamite.logic.responsible.BlockMatchResponsible;
import me.xkyrell.kstnt.dynamite.logic.responsible.EntityMatchResponsible;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

@Getter
@AllArgsConstructor
public abstract class AbstractAttribute<V> implements Attribute<V>, BlockMatchResponsible, EntityMatchResponsible {

    private static final boolean ALWAYS_MATCHES = true;

    private final String name;
    @Setter
    private V value;

    @Override
    public boolean matchesBlock(Block block) {
        return ALWAYS_MATCHES;
    }

    @Override
    public boolean matchesEntity(Entity entity) {
        return ALWAYS_MATCHES;
    }
}
