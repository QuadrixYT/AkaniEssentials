package it.einjojo.akani.essentials.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import it.einjojo.akani.core.api.player.AkaniPlayer;
import it.einjojo.akani.essentials.AkaniEssentialsPlugin;
import it.einjojo.akani.essentials.util.MessageKey;
import org.apache.logging.log4j.message.Message;
import org.bukkit.entity.Player;

@CommandAlias("teleport|tp")
public class TeleportCommand extends BaseCommand {
    private final AkaniEssentialsPlugin plugin;

    public TeleportCommand(AkaniEssentialsPlugin plugin) {
        this.plugin = plugin;
        plugin.commandManager().registerCommand(this);
    }


    @Default
    @Description("Teleport to a player")
    @CommandCompletion("@akaniplayers @akaniplayers")
    public void teleportPlayer(Player bukkitSender, AkaniPlayer target, @Optional @Flags("includeSender") AkaniPlayer target2) {
        if (bukkitSender.getUniqueId().equals(target.uuid())) {
            plugin.sendMessage(bukkitSender, MessageKey.of("teleport.not_self"));
            return;
        }
        AkaniPlayer sender = plugin.core().playerManager().onlinePlayer(bukkitSender.getUniqueId()).orElseThrow();
        target.location().thenAccept((loc) -> {
            plugin.core().messageManager().sendMessage(target, MessageKey.of("teleport.teleporting"));
            sender.teleport(loc);
        }).exceptionally((e) -> {
            plugin.sendMessage(bukkitSender, MessageKey.GENERIC_ERROR);
            e.printStackTrace();
            return null;
        });

    }


}
