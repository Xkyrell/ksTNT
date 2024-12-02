package me.xkyrell.kstnt.dagger;

import dagger.Component;
import me.xkyrell.kstnt.command.impl.MainCommand;
import me.xkyrell.kstnt.dagger.modules.AttributeModule;
import me.xkyrell.kstnt.dagger.modules.DefaultModule;
import me.xkyrell.kstnt.dagger.modules.ServiceModule;
import me.xkyrell.kstnt.listener.DynamiteListener;

@Component(modules = { AttributeModule.class, ServiceModule.class, DefaultModule.class })
public interface TNTComponent {

    DynamiteListener getDynamiteListener();

    MainCommand getCommand();

}
