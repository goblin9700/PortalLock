package portalLoc;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	Logger log = getLogger();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PortalCreater(), this);
		log.info("PortalLoc on enabled.");
		log.info("Создание порталов отключено.");
	}
	
	@Override
	public void onDisable() {
		log.info("PortalLoc has disabled.");
	}
	
	private class PortalCreater implements Listener {
		Player player;
		@EventHandler
		public void portalLock(PortalCreateEvent event) {
			if (player == null) {
				player.sendMessage("Create portal has disable.");
				event.setCancelled(true);
			}
		}
	}

}
