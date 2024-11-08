package me.xkyrell.kstnt.dynamite.attribute;

public interface Attribute<V> {

    String getName();

    void setValue(V value);

    V getValue();

}
