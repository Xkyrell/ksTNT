package me.xkyrell.kstnt.dagger.modules;

import dagger.Module;
import dagger.Provides;
import me.xkyrell.kstnt.dynamite.attribute.Attribute;
import me.xkyrell.kstnt.dynamite.attribute.AttributeRegistry;
import me.xkyrell.kstnt.dynamite.attribute.resolver.AttributeResolver;
import me.xkyrell.kstnt.dynamite.attribute.resolver.impl.SimpleAttributeResolver;
import java.util.Map;

@Module
public class AttributeModule {

    private final AttributeResolver resolver;

    public AttributeModule(Map<String, Attribute<?>> attributes) {
        resolver = new SimpleAttributeResolver(attributes);
    }

    public AttributeModule() {
        this(AttributeRegistry.all());
    }

    @Provides
    public AttributeResolver provideAttributeResolver() {
        return resolver;
    }
}
