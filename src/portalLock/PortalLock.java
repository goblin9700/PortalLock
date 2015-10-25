/*
 * PortalLoc - a bukkit plugin
 * Copyright 2015  goblin9700  (email: alexgrist@yandex.ua)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package portalLock;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;
import portalLock.commands.PLCommand;

public class PortalLock extends JavaPlugin{
		
	protected static PortalLock plugin;
	protected YamlConfiguration config;
	public Boolean enableLock;
	public String messReload;
	public String messNoPerm;
	public String messDenyCreate;
	
	@Override
	public void onEnable() {
		
		plugin = this;
		
		plugin.getLogger().info("PortalLoc on enabled.");
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		saveConfig();
		loadConfig();
		
		getServer().getPluginManager().registerEvents(new PortalCreater(), this);
		
		getCommand("portallock").setExecutor(new PLCommand(this));
	}
	
	public void loadConfig() {	
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(),"config.yml"));
		
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
						player.sendMessage(plugin.messDenyCreate);
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
