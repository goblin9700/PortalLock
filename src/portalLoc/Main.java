package portalLoc;

import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	Logger log = getLogger();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		log.info("PortalLoc on enabled.");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void portalLoc(PortalCreateEvent event) {
		event.setCancelled(true);
		
	}

}
