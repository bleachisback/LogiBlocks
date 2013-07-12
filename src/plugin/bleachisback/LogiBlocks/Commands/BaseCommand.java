package plugin.bleachisback.LogiBlocks.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import plugin.bleachisback.LogiBlocks.LogiBlocksMain;

public abstract class BaseCommand 
{
	protected LogiBlocksMain plugin;
	
	public BaseCommand(LogiBlocksMain plugin)
	{
		this.plugin=plugin;
	}
	
	public abstract boolean execute(CommandSender sender, String[] args, Location location);
}
