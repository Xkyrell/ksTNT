package me.xkyrell.kstnt.dagger.modules;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import lombok.RequiredArgsConstructor;
import me.xkyrell.kstnt.config.GeneralConfig;
import me.xkyrell.kstnt.config.LanguageConfig;
import me.xkyrell.kstnt.dynamite.attribute.resolver.AttributeResolver;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import org.bukkit.plugin.Plugin;

@Module
@RequiredArgsConstructor
public class DefaultModule {

    private final Plugin plugin;

    @Provides
    @Reusable
    public GeneralConfig provideGeneral(DynamiteService dynamiteService, AttributeResolver attributeResolver) {
        return new GeneralConfig(plugin, dynamiteService, attributeResolver);
    }
    @Provides
    @Reusable
    public LanguageConfig provideLanguage() {
        return new LanguageConfig(plugin);
    }
    @Provides
    public Plugin providePlugin() {
        return plugin;
    }
}
