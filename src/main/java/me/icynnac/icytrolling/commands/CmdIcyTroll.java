package me.icynnac.icytrolling.commands;

import me.icynnac.icytrolling.Main;
import me.icynnac.icytrolling.utils.InvalidCommand;
import me.icynnac.icytrolling.utils.ServerVersion;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.icynnac.icytrolling.utils.InteractiveMessage.sendInteractiveMessage;

public class CmdIcyTroll implements TabExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("icytroll.icytroll")) {
            if ((args.length > 0 && !args[0].equalsIgnoreCase("config")) || args.length == 0) sender.sendMessage("§3§m--------------|§b§lIcyTrolling§3§m|--------------");
            if (args.length > 0) {
                PluginDescriptionFile pluginYML = Main.plugin.getDescription();
                switch(args[0].toLowerCase()) {
                    default:
                        mainMenu(sender);
                        break;
                    case "burn":
                        sender.sendMessage("§bSet someone on fire, for however long you please!");
                        sender.sendMessage("§b§oOr at least however long the integer limit allows...");
                        sender.sendMessage("§aTip: Every second is 20 ticks, use that to decide the length.");
                        sendInteractiveMessage(sender, "§6Usage: /burn (target player's name) (burn time in ticks)", "/burn ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /burn");
                        break;
                    case "demo":
                        sender.sendMessage("§bSend someone a demo screen.");
                        sender.sendMessage("§bThey should have bought the game.");
                        sendInteractiveMessage(sender, "§6Usage: /demo (target player's name)", "/demo ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /demo");
                        break;
                    case "drop":
                        sender.sendMessage("§bMake someone drop an item in their hands.");
                        sender.sendMessage("§bHow clumsy!");
                        if (ServerVersion.get.roundedFromServer().getId() < 9) sendInteractiveMessage(sender, "§6Usage: /drop (target player's name)", "/drop ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /drop");
                        else sendInteractiveMessage(sender, "§6Usage: /drop (target player's name) [main/offhand/both]", "/drop ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /drop");
                        break;
                    case "explode":
                        sender.sendMessage("§bMake someone explode.");
                        sender.sendMessage("§bI mean, what other information do you need?");
                        sender.sendMessage("§aTip: The maximum explosion power is 15, anything above crashed Icynnac's test server.");
                        sender.sendMessage("§cWarning: This command damages terrain, do not use this near any builds.");
                        sendInteractiveMessage(sender, "§6Usage: /explode (target player's name) (explosion power)", "/explode ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /explode");
                        break;
                    case "fling":
                        sender.sendMessage("§bSend someone to the sun!");
                        sender.sendMessage("§b(May send some people to the moon, depends on time of day.)");
                        sender.sendMessage("§aTip: The maximum fling power is 4, anything above simply didn't work.");
                        sendInteractiveMessage(sender, "§6Usage: /fling (target player's name) (fling power)", "/fling ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /fling");
                        break;
                    case "info":
                        sender.sendMessage("§3Made by " + "§bIcynnac");
                        sender.sendMessage("§3Version: §b" + pluginYML.getVersion());
                        sender.sendMessage("§3GitHub:");
                        sendInteractiveMessage(sender, "§bhttps://github.com/icynnac/IcyTrolling", "https://github.com/icynnac/IcyTrolling", ClickEvent.Action.OPEN_URL, "Click to open the GitHub site");
                        break;
                    case "lag":
                        sender.sendMessage("§bMake someone have my internet temporarily.");
                        sendInteractiveMessage(sender, "§6Usage: /lag (target player's name) (lag time)", "/lag ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /lag");
                        break;
                    case "levitate":
                        sender.sendMessage("§bSend someone to the sun!");
                        sender.sendMessage("§b(OK, same concept as /fling, but this one lasts a LOOOONG while.");
                        sender.sendMessage("§aTip: The maximum levitate power at the moment is 5, will change that later.");
                        if (ServerVersion.get.roundedFromServer().getId() < 9) sender.sendMessage("§cThis command is disabled in your version!");
                        else sendInteractiveMessage(sender, "§6Usage: /levitate (target player's name) (levitation power)", "/levitate ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /levitate");
                        break;
                    case "pumpkin":
                        sender.sendMessage("§bPut a really inconvenient hat on someone's head!");
                        sender.sendMessage("§b(It's very inconvenient because it has curse of binding.)");
                        if (ServerVersion.get.roundedFromServer().getId() < 11) sender.sendMessage("§cThis command is disabled in your version!");
                        else sendInteractiveMessage(sender, "§6Usage: /pumpkin (target player's name) [on/off]", "/pumpkin ", ClickEvent.Action.SUGGEST_COMMAND, "Click to use /pumpkin");
                        break;
                    case "config":
                        if (args.length > 1) {
                            switch (args[1].toLowerCase()) {
                                case "reset":
                                    Main.plugin.saveResource("config.yml", true);
                                    sender.sendMessage(Main.prefix + "§b config.yml has been reset to default.");
                                    Main.plugin.reloadConfig();
                                    return true;
                                case "reload":
                                    Main.plugin.reloadConfig();
                                    sender.sendMessage(Main.prefix + "§b config.yml has been reloaded.");
                                    return true;
                            } InvalidCommand.CONFIG.sendMessage(sender);
                        } else InvalidCommand.CONFIG.sendMessage(sender);
                }
            } else mainMenu(sender);
            if ((args.length > 0 && !args[0].equalsIgnoreCase("config")) || args.length == 0) sender.sendMessage("§3§m--------------|-----------|--------------");
        } return true;
    }

    void mainMenu(CommandSender sender) {
        sendInteractiveMessage(sender, "§b§l/burn §3- Light someone on fire!", "/icytroll burn", ClickEvent.Action.RUN_COMMAND, "Click to see /burn info.");
        sendInteractiveMessage(sender, "§b§l/demo §3- Classic Demo Screen troll.", "/icytroll demo", ClickEvent.Action.RUN_COMMAND,  "Click to see /demo info.");
        sendInteractiveMessage(sender, "§b§l/drop §3- Make someone drop what's in their hands.", "/icytroll drop", ClickEvent.Action.RUN_COMMAND,  "Click to see /drop info.");
        sendInteractiveMessage(sender, "§b§l/explode §c- Kaboom.", "/icytroll explode", ClickEvent.Action.RUN_COMMAND,  "Click to see /explode info.");
        sendInteractiveMessage(sender, "§b§l/fling §3- Throw someone in the air.", "/icytroll fling", ClickEvent.Action.RUN_COMMAND,  "Click to see /fling info.");
        sendInteractiveMessage(sender, "§b§l/lag §3- Give someone my internet.", "/icytroll lag", ClickEvent.Action.RUN_COMMAND,  "Click to see /lag info.");
        if (ServerVersion.get.roundedFromServer().getId() > 8) sendInteractiveMessage(sender, "§b§l/levitate §3- Send someone slowly to the sun.", "/icytroll levitate", ClickEvent.Action.RUN_COMMAND,  "Click to see /levitate info.");
        if (ServerVersion.get.roundedFromServer().getId() > 10) sendInteractiveMessage(sender, "§b§l/pumpkin §3- Give someone a pumpkin hat!", "/icytroll pumpkin", ClickEvent.Action.RUN_COMMAND,  "Click to see /pumpkin info.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) return new ArrayList<>(Arrays.asList("config", "demo",
                "drop", "explode", "burn", "fling", "info", "lag", "levitate", "pumpkin"));
        if (args.length == 2) if (args[0].equalsIgnoreCase("config"))
            return new ArrayList<>(Arrays.asList("reset", "reload"));
        return new ArrayList<>();
    }
}
