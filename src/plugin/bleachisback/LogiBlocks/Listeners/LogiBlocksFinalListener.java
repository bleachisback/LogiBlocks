package plugin.bleachisback.LogiBlocks.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import plugin.bleachisback.LogiBlocks.LogiBlocksMain;

public class LogiBlocksFinalListener implements Listener {
	LogiBlocksMain plugin;
	
	public LogiBlocksFinalListener(LogiBlocksMain plugin) {	
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChatFinal(AsyncPlayerChatEvent e) {
		plugin.triggerListener("chat", e.getPlayer().getDisplayName());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent e) {
		final String name = e.getPlayer().getDisplayName();
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				plugin.triggerListener("login", name);
			}
		}, 1);		
	}
}
