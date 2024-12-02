package me.xkyrell.kstnt.dynamite.attribute;

import me.xkyrell.kstnt.dynamite.attribute.impl.AftermathAttribute;
import me.xkyrell.kstnt.dynamite.attribute.impl.FuseAttribute;
import me.xkyrell.kstnt.dynamite.attribute.impl.RadiusAttribute;
import me.xkyrell.kstnt.dynamite.attribute.impl.YieldAttribute;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AttributeRegistry {

    private static final Attribute<String> AFTERMATH = new AftermathAttribute("FIRE");
    private static final Attribute<Integer> FUSE = new FuseAttribute(8);
    private static final Attribute<Double> RADIUS = new RadiusAttribute(4.0);
    private static final Attribute<Double> YIELD = new YieldAttribute(1.0);

    private static final List<Attribute<?>> ATTRIBUTES = List.of(AFTERMATH, FUSE, RADIUS, YIELD);

    public static Map<String, Attribute<?>> all() {
        return ATTRIBUTES.stream().collect(
                Collectors.toUnmodifiableMap(Attribute::getName, Function.identity())
        );
    }
}
