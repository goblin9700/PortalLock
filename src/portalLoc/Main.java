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
		log.info("Создание порталов отключено.");
	}
	
	@Override
	public void onDisable() {
		log.info("PortalLoc has disabled.");
	}
	
	@EventHandler
	public void portalLoc(PortalCreateEvent event) {
		event.getPlayer().sendMessage("Create portal has disable.");
		event.setCancelled(true);
		
		
	}

}
