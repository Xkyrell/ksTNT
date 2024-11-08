package me.xkyrell.kstnt.dynamite.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public abstract class AbstractAttribute<V> implements Attribute<V> {

    private final String name;
    @Setter
    private V value;

}
