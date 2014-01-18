package net.winneonsword.JM;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.winneonsword.JM.Utils.*;

public class UnknownArgumentException extends Exception {

	private static final long serialVersionUID = 8541585036940757362L;
	
	public UnknownArgumentException(Player p, String cmd){
		
		s(p, "&cUnknown argument! Try &6" + cmd + "&c.");
		
	}
	
	public UnknownArgumentException(CommandSender s, String cmd){
		
		ss(s, "&cUnknown argument! Try &6" + cmd + "&c.");
		
	}
	
}
