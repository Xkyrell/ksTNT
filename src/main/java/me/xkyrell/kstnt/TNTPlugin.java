package me.xkyrell.kstnt;

import me.xkyrell.kstnt.command.impl.MainCommand;
import me.xkyrell.kstnt.dagger.DaggerTNTComponent;
import me.xkyrell.kstnt.dagger.TNTComponent;
import me.xkyrell.kstnt.dagger.modules.AttributeModule;
import me.xkyrell.kstnt.dagger.modules.DefaultModule;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTPlugin extends JavaPlugin {

    private static final String[] LOGO = {
            " _  __ __ _____ __  _ _____   ",
            "| |/ /' _/_   _|  \\| |_   _| ",
            "|   <`._`. | | | | ' | | |    ",
            "|_|\\_\\___/ |_| |_|\\__| |_| ",
            "",
            "      Plugin by Xkyrell",
            ""
    };

    private MainCommand command;

    @Override
    public void onEnable() {
        TNTComponent component = DaggerTNTComponent.builder()
                .attributeModule(new AttributeModule())
                .defaultModule(new DefaultModule(this))
                .build();

        getServer().getCommandMap().register(
                getName(), (command = component.getCommand())
        );

        getServer().getPluginManager().registerEvents(
                component.getDynamiteListener(), this
        );

        for (String line : LOGO) {
            getLogger().info(line);
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        if (command != null) {
            command.unregister(this);
        }
    }
}