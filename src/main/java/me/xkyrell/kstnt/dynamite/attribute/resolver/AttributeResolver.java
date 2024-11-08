package me.xkyrell.kstnt.dynamite.attribute.resolver;

import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import java.util.Optional;

public interface AttributeResolver {

    Optional<Attribute<?>> resolve(String name);

}
