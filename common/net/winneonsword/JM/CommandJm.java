package net.winneonsword.JM;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.winneonsword.JM.Utils.*;

public class CommandJm implements CommandExecutor {
	
	private JM main;
	
	public CommandJm(JM main){
		
		this.main = main;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("jm")){
			
			Player p = (Player) sender;
			
			if (args.length == 0){
				
				s(p, "Try /jm ?.");
				
			} else {
				
				switch (args[0].toLowerCase()){
				
				default:
					
					s(p, "&cUnknown command! Try /jm ? for help.");
					break;
				
				case "?": case "help":
					
					s(p, new String[] {
							
							JM + "Help Panel",
							"&7- &e/jm ? &6// &eView the help panel."
							
					});
					break;
					
				}
				
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
}
