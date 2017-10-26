package net.johnbrooks.voidproof;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class VoidProof extends JavaPlugin
{
    // TODO: Load Multiverse first! DEPEND
    private static VoidProof plugin;

    public static VoidProof getPlugin()
    {
        return plugin;
    }

    private Location transportTo;
    private String[] enabledWorlds;

    @Override
    public void onEnable()
    {
        plugin = this;
        saveDefaultConfig();
        readConfiguration();
        getServer().getPluginManager().registerEvents(new VoidListener(), this);
        getLogger().info(getDescription().getName() + " is now enabled.");
    }

    @Override
    public void onDisable()
    {
        getLogger().info(getDescription().getName() + " is now disabled.");
    }

    public boolean isEnabledWorld(String world)
    {
        if (enabledWorlds != null)
            for (String w : enabledWorlds)
                if (w.equalsIgnoreCase(world))
                    return true;
        return false;
    }

    public Location getTransportTo()
    {
        return transportTo;
    }

    private void readConfiguration()
    {
        // Transport data
        World world = Bukkit.getWorld(getConfig().getString("transport.world"));
        int x = getConfig().getInt("transport.x"), y = getConfig().getInt("transport.y"), z = getConfig().getInt("transport.z");
        int yaw = getConfig().getInt("transport.yaw"), pitch = getConfig().getInt("transport.pitch");
        transportTo = new Location(world, x, y, z, yaw, pitch);

        // Enabled-worlds data
        List<String> enabledWorlds = getConfig().getStringList("enabled-worlds");
        this.enabledWorlds = enabledWorlds.toArray(new String[enabledWorlds.size()]);
    }
}
