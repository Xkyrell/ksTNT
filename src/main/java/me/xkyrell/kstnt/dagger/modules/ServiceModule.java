package me.xkyrell.kstnt.dagger.modules;

import dagger.Binds;
import dagger.Module;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import me.xkyrell.kstnt.dynamite.service.impl.SimpleDynamiteService;

@Module
public interface ServiceModule {

    @Binds
    DynamiteService dynamiteService(SimpleDynamiteService __);

}
