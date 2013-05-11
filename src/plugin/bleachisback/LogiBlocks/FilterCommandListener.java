package plugin.bleachisback.LogiBlocks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FilterCommandListener implements CommandExecutor
{
	private LogiBlocksMain plugin;
	
	private HashMap<PluginCommand,CommandExecutor> executors=new HashMap<PluginCommand,CommandExecutor>();
	
	public FilterCommandListener(LogiBlocksMain plugin)
	{
		this.plugin=plugin;
		for(Plugin _plugin:Bukkit.getPluginManager().getPlugins())
		{
			if(_plugin.getDescription().getCommands()==null)
			{
				continue;
			}
			for(String cmdString:_plugin.getDescription().getCommands().keySet())
			{
				PluginCommand cmd=Bukkit.getPluginCommand(cmdString);
				if(cmd==null)
				{
					this.plugin.log.info("Null detected at command: "+cmdString);
					continue;
				}
				executors.put(cmd, cmd.getExecutor());
				cmd.setExecutor(this);
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) 
	{
		if(sender instanceof Player||sender instanceof BlockCommandSender)
		{
			Location loc=null;
			if(sender instanceof Player)
			{
				loc=((Player)sender).getLocation();
			}
			else if(sender instanceof BlockCommandSender)
			{
				loc=((BlockCommandSender)sender).getBlock().getLocation();
			}
			if(!LogiBlocksMain.filter(args, sender, cmd, loc))
			{
				return true;
			}
		}
		if(!executors.containsKey(cmd))
		{
			return false;
		}
		return executors.get(cmd).onCommand(sender, cmd, alias, args);
	}

}
