package plugin.bleachisback.LogiBlocks;

import org.bukkit.command.BlockCommandSender;

public interface FlagListener 
{
	public boolean onFlag(String flag, String[] args, BlockCommandSender sender) throws FlagFailureException;
}
