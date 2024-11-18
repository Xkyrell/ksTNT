package me.xkyrell.kstnt.command.impl;

import me.xkyrell.kstnt.command.AbstractSubCommand;
import me.xkyrell.kstnt.config.GeneralConfig;
import me.xkyrell.kstnt.config.LanguageConfig;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadSubCommand extends AbstractSubCommand {

    private final GeneralConfig general;
    private final LanguageConfig language;

    ReloadSubCommand(GeneralConfig general, LanguageConfig language) {
        super("reload", 1);

        this.general = general;
        this.language = language;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        language.reload();
        general.reload();

        sender.sendMessage(language.getPrefixedMsg("reload"));
        return false;
    }
}
