package me.icynnac.icytrolling;

import me.icynnac.icytrolling.commands.*;
import me.icynnac.icytrolling.events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static HashMap<UUID, ItemStack> playerPumpkinHeads = new HashMap<>();
    public static String prefix;

    @Override
    public void onEnable() {
        plugin = this;
        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.prefix"));

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

        getCommand("levitate").setPermissionMessage("§cno.");
        getCommand("icytroll").setPermissionMessage("§cno.");
        getCommand("explode").setPermissionMessage("§cno.");
        getCommand("pumpkin").setPermissionMessage("§cno.");
        getCommand("fling").setPermissionMessage("§cno.");
        getCommand("demo").setPermissionMessage("§cno.");
        getCommand("burn").setPermissionMessage("§cno.");
        getCommand("drop").setPermissionMessage("§cno.");
        getCommand("lag").setPermissionMessage("§cno.");

        getCommand("levitate").setExecutor(new CmdLevitate());
        getCommand("icytroll").setExecutor(new CmdIcyTroll());
        getCommand("explode").setExecutor(new CmdExplode());
        getCommand("pumpkin").setExecutor(new CmdPumpkin());
        getCommand("fling").setExecutor(new CmdFling());
        getCommand("demo").setExecutor(new CmdDemo());
        getCommand("burn").setExecutor(new CmdBurn());
        getCommand("drop").setExecutor(new CmdDrop());
        getCommand("lag").setExecutor(new CmdLag());

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        for (UUID uuid : playerPumpkinHeads.keySet())
            Bukkit.getOfflinePlayer(uuid).getPlayer().getEquipment().setHelmet(playerPumpkinHeads.get(uuid));
    }
}
