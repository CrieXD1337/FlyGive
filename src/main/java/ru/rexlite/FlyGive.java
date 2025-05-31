package ru.rexlite;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import java.io.File;

public class FlyGive extends PluginBase implements Listener {
    public FlyGive() {
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveResource("config.yml");
        this.getLogger().info(TextFormat.BLUE + "FlyGive v1.0 " + TextFormat.DARK_AQUA + "Enabled!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        Config cfg = new Config(new File(this.getDataFolder(), "config.yml"));
        if (cmd.getName().toLowerCase().equals("flygive")) {
            if (args.length < 2) {
                sender.sendMessage(TextFormat.colorize(cfg.getString("use")));
            } else if (this.getServer().getPlayer(args[0]) != null) {
                if (args[1].equals("true")) {
                    this.getServer().getPlayer(args[0]).setAllowFlight(true);
                    sender.sendMessage(TextFormat.colorize(cfg.getString("true-fly").replace("{nick}", this.getServer().getPlayer(args[0]).getName())));
                    if (cfg.getBoolean("is-use-messages-for-players")) {
                        this.getServer().getPlayer(args[0]).sendMessage(TextFormat.colorize(cfg.getString("message-true")));
                    }
                } else {
                    this.getServer().getPlayer(args[0]).setAllowFlight(false);
                    sender.sendMessage(TextFormat.colorize(cfg.getString("false-fly").replace("{nick}", this.getServer().getPlayer(args[0]).getName())));
                    if (cfg.getBoolean("is-use-messages-for-players")) {
                        this.getServer().getPlayer(args[0]).sendMessage(TextFormat.colorize(cfg.getString("message-false")));
                    }
                }
            } else {
                sender.sendMessage(TextFormat.colorize(cfg.getString("no-found").replace("{nick}", args[0])));
            }
        }

        return false;
    }
}
