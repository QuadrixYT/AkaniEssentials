package it.einjojo.akani.essentials.command.economy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import it.einjojo.akani.core.api.player.AkaniOfflinePlayer;
import it.einjojo.akani.essentials.AkaniEssentialsPlugin;
import org.bukkit.entity.Player;

@CommandAlias("pay")
public class PayCommand extends BaseCommand {

    private final AkaniEssentialsPlugin plugin;

    public PayCommand(AkaniEssentialsPlugin plugin) {
        this.plugin = plugin;
        plugin.commandManager().registerCommand(this);
    }


    @Default
    @CommandCompletion("@akaniplayers 1")
    @Syntax("<player> <amount>")
    public void pay(Player sender, AkaniOfflinePlayer target, double amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @CatchUnknown
    public void unknown(Player sender) {
        plugin.sendCommandUsageMessage(sender, "/pay <player> <amount>");
    }

}
