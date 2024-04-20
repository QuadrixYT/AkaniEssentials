package it.einjojo.akani.essentials;

import com.google.gson.Gson;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteCommandsBukkit;
import it.einjojo.akani.core.api.AkaniCoreProvider;
import it.einjojo.akani.core.api.litecommands.OfflinePlayerResolver;
import it.einjojo.akani.core.api.litecommands.OnlinePlayerResolver;
import it.einjojo.akani.core.api.player.AkaniOfflinePlayer;
import it.einjojo.akani.core.api.player.AkaniPlayer;
import it.einjojo.akani.core.paper.PaperAkaniCore;
import it.einjojo.akani.essentials.command.TeleportCommand;
import it.einjojo.akani.essentials.util.EssentialsMessageProvider;
import it.einjojo.akani.essentials.warp.WarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AkaniEssentialsPlugin extends JavaPlugin {
    public static final String PERMISSION_BASE = "akani.essentials.";
    private PaperAkaniCore core;
    private WarpManager warpManager;
    private LiteCommands<CommandSender> commands;
    private Gson gson;

    @Override
    public void onEnable() {
        initClasses();
        commands = LiteCommandsBukkit.builder("essentials", this)
                .argument(AkaniPlayer.class, new OnlinePlayerResolver<>(core))
                .argument(AkaniOfflinePlayer.class, new OfflinePlayerResolver<>(core))
                .commands(
                        new TeleportCommand(core)
                )
                .build();
        commands.register();
    }

    public PaperAkaniCore core() {
        return core;
    }

    public WarpManager warpManager() {
        return warpManager;
    }

    public LiteCommands<CommandSender> commands() {
        return commands;
    }

    public Gson gson() {
        return gson;
    }

    private void initClasses() {
        core.registerMessageProvider(new EssentialsMessageProvider());
        gson = new Gson();
        core = (PaperAkaniCore) AkaniCoreProvider.get();
        warpManager = new WarpManager(this);
        warpManager.load();

    }

    @Override
    public void onDisable() {
        if (commands != null) commands.unregister();
    }
}