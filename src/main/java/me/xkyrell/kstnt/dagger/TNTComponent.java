package me.xkyrell.kstnt.dagger;

import dagger.Component;
import me.xkyrell.kstnt.command.impl.MainCommand;
import me.xkyrell.kstnt.listener.DynamiteListener;

@Component(modules = { })
public interface TNTComponent {

    DynamiteListener getDynamiteListener();

    MainCommand getCommand();

}
