package me.xkyrell.kstnt.command.impl;

import lombok.Getter;
import me.xkyrell.kstnt.command.AbstractCommand;
import me.xkyrell.kstnt.command.AbstractSubCommand;
import me.xkyrell.kstnt.config.GeneralConfig;
import me.xkyrell.kstnt.config.LanguageConfig;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainCommand extends AbstractCommand {

    @Getter
    private final Set<AbstractSubCommand> subCommands = new HashSet<>();
    private final DynamiteService dynamiteService;
    private final GeneralConfig general;
    private final LanguageConfig language;
    private final Plugin plugin;

    @Inject
    public MainCommand(DynamiteService dynamiteService, GeneralConfig general, LanguageConfig language, Plugin plugin) {
        super("kstnt", List.of("tnt", "dynamite", "dynamites"));

        this.dynamiteService = dynamiteService;
        this.general = general;
        this.language = language;
        this.plugin = plugin;

        setUnknownSubCommand(sender -> {
            Component unknownSubCommand = language.getPrefixedMsg("subcommand-not-exist");
            sender.sendMessage(unknownSubCommand.replaceText(builder -> {
                builder.matchLiteral("{label}").replacement(getUsage());
            }));
        });

        addAvailability(
                sender -> !sender.hasPermission("kstnt.admin"),
                language.getPrefixedMsg("no-permission")
        );

        setUnknownArgExecuting(sender -> {
            sender.sendMessage(language.getPrefixedMsg("not-enough-args"));
        });

        setupSubCommands();
    }

    private void setupSubCommands() {
        subCommands.add(new GiveSubCommand(dynamiteService, language, plugin));
        subCommands.add(new ReloadSubCommand(general, language));
    }
}
