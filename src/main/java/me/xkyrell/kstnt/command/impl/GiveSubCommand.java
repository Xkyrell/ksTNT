package me.xkyrell.kstnt.command.impl;

import me.xkyrell.kstnt.command.AbstractSubCommand;
import me.xkyrell.kstnt.config.LanguageConfig;
import me.xkyrell.kstnt.dynamite.Dynamite;
import me.xkyrell.kstnt.dynamite.service.DynamiteService;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GiveSubCommand extends AbstractSubCommand {

    private final DynamiteService dynamiteService;
    private final LanguageConfig language;
    private final Plugin plugin;

    GiveSubCommand(DynamiteService dynamiteService, LanguageConfig language, Plugin plugin) {
        super("give", 4);

        this.dynamiteService = dynamiteService;
        this.language = language;
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        Player target = plugin.getServer().getPlayerExact(args[1]);
        if (target == null) {
            sender.sendMessage(language.getPrefixedMsg("player-offline"));
            return true;
        }

        Optional<Dynamite> dynamiteOptional = dynamiteService.getResolver().resolve(args[2]);
        if (dynamiteOptional.isEmpty()) {
            Component unknownTNT = language.getPrefixedMsg("unknown-tnt");
            sender.sendMessage(unknownTNT.replaceText(builder -> {
                builder.matchLiteral("{tnt-type}").replacement(args[2]);
            }));
            return true;
        }

        Optional<Integer> amountOptional = safeParseAmount(args[3]);
        if (amountOptional.isEmpty()) {
            sender.sendMessage(language.getPrefixedMsg("not-integer"));
            return true;
        }

        Dynamite dynamite = dynamiteOptional.get();
        ItemStack itemStack = dynamite.getIcon().compose();
        itemStack.setAmount(amountOptional.get());
        target.getInventory().addItem(itemStack);

        Map<String, String> placeholders = Map.of(
                "{receiver}", target.getName(),
                "{sender}", sender.getName(),
                "{tnt-type}", args[2],
                "{amount}", args[3]
        );

        sender.sendMessage(language.getPrefixedMsg("give-tnt", placeholders));
        target.sendMessage(language.getPrefixedMsg("take-tnt", placeholders));
        return false;
    }

    private Optional<Integer> safeParseAmount(String amount) {
        try {
            return Optional.of(Integer.parseInt(amount));
        }
        catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) throws IllegalArgumentException {
        return TabCompleter.create()
                .from(1)
                .supply(getOnlinePlayers())
                .supply(getDynamiteNames())
                .toSuggestions(args);
    }

    private List<String> getOnlinePlayers() {
        return plugin.getServer().getOnlinePlayers().stream()
                .map(Player::getName)
                .toList();
    }

    private List<String> getDynamiteNames() {
        return dynamiteService.getResolver().getDynamites().stream()
                .map(Dynamite::getName)
                .toList();
    }

    @Override
    public @NotNull String getUsage() {
        return "give <player> <tnt-type> <amount>";
    }
}
