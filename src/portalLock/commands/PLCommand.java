package portalLock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import portalLock.PortalLock;

public class PLCommand implements CommandExecutor {
	
	private PortalLock plugin;

	public PLCommand(PortalLock plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("portallock.reload")) {
						plugin.reloadConfig();
						plugin.loadConfig();
						sender.sendMessage(plugin.messReload);
					}else{
						sender.sendMessage(plugin.messNoPerm);
					}
				}
			}
		} else {
			sender.sendMessage("PortalLock " + "This is an in-game only command");
			return true;
		}
		return true;
	}
}
