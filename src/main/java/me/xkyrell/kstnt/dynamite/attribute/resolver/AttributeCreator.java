package me.xkyrell.kstnt.dynamite.attribute.resolver;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;

public interface AttributeCreator {

    <V, A extends Attribute<V>> A getOrCreate(@NonNull A attribute);

    default void create(Attribute<?> attribute) {
        getOrCreate(attribute);
    }

    default void createAttributes(Attribute<?>... attributes) {
        for (Attribute<?> attribute : attributes) {
            getOrCreate(attribute);
        }
    }
}
