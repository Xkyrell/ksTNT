package me.xkyrell.kstnt.dynamite.attribute.resolver.impl;

import lombok.NonNull;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.attribute.resolver.AttributeCreator;
import me.xkyrell.kstnt.dynamite.attribute.resolver.AttributeResolver;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleAttributeResolver implements AttributeResolver, AttributeCreator {

    private final Map<String, Attribute<?>> attributes;

    public SimpleAttributeResolver(Map<String, Attribute<?>> attributes) {
        this.attributes = new HashMap<>(attributes);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V, A extends Attribute<V>> A getOrCreate(@NonNull A attribute) {
        return (A) attributes.computeIfAbsent(attribute.getName(), __ -> attribute);
    }

    @Override
    public Optional<Attribute<?>> resolve(String name) {
        return Optional.ofNullable(attributes.get(name));
    }
}
