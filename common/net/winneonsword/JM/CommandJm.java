package net.winneonsword.JM;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.winneonsword.JM.Utils.*;

public class CommandJm implements CommandExecutor {
	
	private JM main;
	
	public CommandJm(JM main){
		
		this.main = main;
		
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		
		try {
			
			if (label.equalsIgnoreCase("jm")){
				
				if (args.length == 0){
					
					ss(s, "Try /jm ?.");
					
				} else {
					
					switch (args[0].toLowerCase()){
					
					default:
						
						throw new UnknownArgumentException(s, "/jm ?");
					
					case "?": case "help":
						
						ss(s, new String[] {
								
								JM + "Help Panel",
								"&7- &e/jm ? &6// &eView the help panel."
								
						});
						break;
						
					case "join":
						
						if (args.length == 1){
							
							ss(s, new String[] {
									
								JM + "Join Help Panel",
								"&7- &e/jm join add <message> &6// &eAdd a join message.",
								"&7- &e/jm join remove <id> &6// &eRemove a join message.",
								"&7- &e/jm join list &6// &eList all of the current join messages."
									
							});
							
						} else {
							
							switch (args[1].toLowerCase()){
							
							default:
								
								throw new UnknownArgumentException(s, "/jm join");
							
							case "add":
								
								
								break;
								
							case "remove":
								
								
								break;
								
							case "list":
								
								
								break;
								
							}
							
						}
						
						break;
						
					case "leave":
						
						if (args.length == 1){
							
							ss(s, new String[] {
									
									JM + "Leave Help Panel",
									"&7- &e/jm leave add <message> &6// &eAdd a leave message.",
									"&7- &e/jm leave remove <id> &6// &eRemove a leave message.",
									"&7- &e/jm leave list &6// &eList all of the current leave messages."
										
								});
							
						} else {
							
							switch (args[1].toLowerCase()){
							
							default:
								
								throw new UnknownArgumentException(s, "/jm leave");
							
							case "add":
								
								
								break;
								
							case "remove":
								
								
								break;
								
							case "list":
								
								
								break;
								
							}
							
						}
						
						break;
						
					}
					
				}
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (UnknownArgumentException e){
			
			// I'm too lazy.
			
			return true;
			
		}
		
	}
	
}
