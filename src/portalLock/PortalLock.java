
package portalLock;

import java.io.File;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

import portalLock.commands.PLCommand;

public class PortalLock extends JavaPlugin{
	
	
	
	protected static PortalLock plugin;
	protected FileConfigurationOptions config;
	public Boolean enableLock;
	public String messReload;
	public String messNoPerm;
	public String messDenyCreate;
	
	@Override
	public void onEnable() {
		
		plugin = this;
		
		plugin.getLogger().info("PortalLoc on enabled.");
		
		config = getConfig().options().copyDefaults(true);
		
		loadConfig();
		
		getServer().getPluginManager().registerEvents(new PortalCreater(), this);
		
		getCommand("portallock").setExecutor(new PLCommand(this));
	}
	
	public void loadConfig() {
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
		
		enableLock = config.getBoolean("enable");
		messReload = ChatColor.translateAlternateColorCodes('&', config.getString("messages.reload"));
		messNoPerm = ChatColor.translateAlternateColorCodes('&', config.getString("messages.nopermission"));
		messDenyCreate = ChatColor.translateAlternateColorCodes('&', config.getString("messages.denycreate"));
		
		saveConfig();
		plugin.getLogger().info("Configuration succeful loaded.");
	}

	@Override
	public void onDisable() {
		plugin.getLogger().info("PortalLoc has disabled.");
	}
	
	
	
	private class PortalCreater implements Listener {
		
		@EventHandler
		public void portalLock(PortalCreateEvent event) {
			if (enableLock) {
				for(Player player: getServer().getOnlinePlayers()) {
					if(!player.hasPermission("portallock.ignore")) {
						event.setCancelled(true);
						player.sendMessage(plugin.messDenyCreate);
					}
				}
			}
		}
	}

}
