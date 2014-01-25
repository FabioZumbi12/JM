package net.winneonsword.JM;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
								"&7- &e/jm ? &6// &eView the help panel.",
								"&7- &e/jm join &6// &eView the join help panel.",
								"&7- &e/jm leave &6// &eView the leave help panel.",
								"&7- &e/jm disable &6// &eDisable JM."
								
						});
						break;
						
					case "join":
						
						if (args.length == 1){
							
							ss(s, new String[] {
									
									JM + "Join Help Panel",
									"&7- &e/jm join add <message> &6// &eAdd a join message.",
									"&7- &e/jm join add player <player> <message> &6// &eAdd a join message to a player.",
									"&7- &e/jm join remove <id> &6// &eRemove a join message.",
									"&7- &e/jm join remove player <player> <id> &6// &eRemove a join message from a player.",
									"&7- &e/jm join list [player] &6// &eList all of the current join messages."
									
							});
							
						} else {
							
							switch (args[1].toLowerCase()){
							
							default:
								
								throw new UnknownArgumentException(s, "/jm join");
							
							case "add":
								
								if (checkPerm(s, "jm.add.join")){
									
									if (args.length == 2){
										
										ss(s, "&c/jm join add <message>");
										
									} else {
										
										if (args[2].equalsIgnoreCase("player")){
											
											if (args.length < 5){
												
												ss(s, "&c/jm join add player <player> <message>");
												
											} else {
												
												JMPlayer jmp = main.getUtils().getPlayer(args[3]);
												
												if (jmp == null){
													
													ss(s, "&cThat player has never joined!");
													
												} else {
													
													String message = stitchString(args, 5);
													
													if (!(message.contains("%p"))){
														
														ss(s, "&cThe %p variable is not present in the message!");
														
													} else {
														
														jmp.addJoinMessage(message);
														
														ss(s, new String[] {
																
																JM + "Added the join message for the player &7" + jmp.getName() + "&7:",
																translateMessage(s, message)
																
														});
														
													}
													
												}
												
											}
											
										} else {
											
											String message = stitchString(args, 3);
											
											if (!(message.contains("%p"))){
												
												ss(s, "&cThe %p variable is not present in the message!");
												
											} else {
												
												main.getUtils().addJoinMessage(message);
												
												ss(s, new String[] {
														
														JM + "Added the join message:",
														translateMessage(s, message)
														
												});
												
											}
											
										}
										
									}
									
								}
								
								break;
								
							case "remove":
								
								if (checkPerm(s, "jm.remove.join")){
									
									if (args.length == 2){
										
										ss(s, "&c/jm join remove <id>");
										
									} else {
										
										if (args[2].equalsIgnoreCase("player")){
											
											if (args.length < 5){
												
												ss(s, "&c/jm join remove player <player> <id>");
												
											} else {
												
												JMPlayer jmp = main.getUtils().getPlayer(args[3]);
												
												if (jmp == null){
													
													ss(s, "&cThat player has never joined!");
													
												} else {
													
													try {
														
														Integer.parseInt(args[4]);
														
													} catch (Exception e){
														
														ss(s, "&cThe ID you entered is not a valid number!");
														
														return true;
														
													}
													
													if (jmp.getJoinMessages().size() < Integer.parseInt(args[4]) + 1){
														
														ss(s, "The player &7" + jmp.getName() + " &7does not have the ID!");
														
													} else {
														
														String message = jmp.getJoinMessages().get(Integer.parseInt(args[4]));												
														jmp.removeJoinMessage(message);
														
														ss(s, new String[] {
																
																JM + "Removed the join message for the player &7" + jmp.getName() + "&7:",
																translateMessage(s, message)
																
														});
														
													}
													
												}
												
											}
											
										} else {
											
											try {
												
												Integer.parseInt(args[2]);
												
											} catch (Exception e){
												
												ss(s, "&cThe ID you entered is not a valid number!");
												
												return true;
												
											}
											
											if (main.getUtils().getJoinMessages().size() < Integer.parseInt(args[2]) + 1){
												
												ss(s, "ID not found!");
												
											} else {
												
												String message = main.getUtils().getJoinMessages().get(Integer.parseInt(args[2]));												
												main.getUtils().removeJoinMessage(message);
												
												ss(s, new String[] {
														
														JM + "Removed the join message:",
														translateMessage(s, message)
														
												});
												
											}
											
										}
										
									}
									
								}
								
								break;
								
							case "list":
								
								if (args.length == 2){
									
									List<String> list = main.getUtils().getJoinMessages();
									ss(s, "Global Join Messages");
									
									for (int i = 0; i < list.size(); i++){
										
										ss(s, new String[]{
											
											"&7- &e" + i + " &6// " + translateMessage(s, list.get(i))
											
										});
										
									}
									
								} else {
									
									JMPlayer jmp = main.getUtils().getPlayer(args[2]);
									
									if (jmp == null){
										
										ss(s, "&cThat player has never joined!");
										
									} else {
										
										OfflinePlayer off = Bukkit.getOfflinePlayer(jmp.getName());
										List<String> list = jmp.getJoinMessages();
										
										if (off.isOnline()){
											
											Player p = off.getPlayer();
											ss(s, p.getDisplayName() + "&7's Join Messages");
											
											for (int i = 0; i < list.size(); i++){
												
												ss(s, new String[]{
													
													"&7- &e" + i + " &6// " + translateMessage(p, list.get(i))
													
												});
												
											}
											
										} else {
											
											ss(s, off.getName() + "&7's Join Messages");
											
											for (int i = 0; i < list.size(); i++){
												
												ss(s, new String[]{
													
													"&7- &e" + i + " &6// " + translateMessage(off, list.get(i))
													
												});
												
											}
											
										}
										
									}
									
								}
								
								break;
								
							}
							
						}
						
						break;
						
					case "leave":
						
						if (args.length == 1){
							
							ss(s, new String[] {
									
									JM + "Leave Help Panel",
									"&7- &e/jm leave add <message> &6// &eAdd a leave message.",
									"&7- &e/jm leave add player <player> <message> &6// &eAdd a leave message to a player.",
									"&7- &e/jm leave remove <id> &6// &eRemove a leave message.",
									"&7- &e/jm leave remove player <player> <id> &6// &eRemove a leave message from a player.",
									"&7- &e/jm leave list [player] &6// &eList all of the current leave messages."
									
							});
							
						} else {
							
							switch (args[1].toLowerCase()){
							
							default:
								
								throw new UnknownArgumentException(s, "/jm leave");
							
							case "add":
								
								if (checkPerm(s, "jm.add.leave")){
									
									if (args.length == 2){
										
										ss(s, "&c/jm leave add <message>");
										
									} else {
										
										if (args[2].equalsIgnoreCase("player")){
											
											if (args.length < 5){
												
												ss(s, "&c/jm leave add player <player> <message>");
												
											} else {
												
												JMPlayer jmp = main.getUtils().getPlayer(args[3]);
												
												if (jmp == null){
													
													ss(s, "&cThat player has never joined!");
													
												} else {
													
													String message = stitchString(args, 5);
													
													if (!(message.contains("%p"))){
														
														ss(s, "&cThe %p variable is not present in the message!");
														
													} else {
														
														jmp.addLeaveMessage(message);
														
														ss(s, new String[] {
																
																JM + "Added the leave message for the player &7" + jmp.getName() + "&7:",
																translateMessage(s, message)
																
														});
														
													}
													
												}
												
											}
											
										} else {
											
											String message = stitchString(args, 3);
											
											if (!(message.contains("%p"))){
												
												ss(s, "&cThe %p variable is not present in the message!");
												
											} else {
												
												main.getUtils().addLeaveMessage(message);
												
												ss(s, new String[] {
														
														JM + "Added the leave message:",
														translateMessage(s, message)
														
												});
												
											}
											
										}
										
									}
									
								}
								
								break;
								
							case "remove":
								
								if (checkPerm(s, "jm.remove.leave")){
									
									if (args.length == 2){
										
										ss(s, "&c/jm leave remove <id>");
										
									} else {
										
										if (args[2].equalsIgnoreCase("player")){
											
											if (args.length < 5){
												
												ss(s, "&c/jm leave remove player <player> <id>");
												
											} else {
												
												JMPlayer jmp = main.getUtils().getPlayer(args[3]);
												
												if (jmp == null){
													
													ss(s, "&cThat player has never joined!");
													
												} else {
													
													try {
														
														Integer.parseInt(args[4]);
														
													} catch (Exception e){
														
														ss(s, "&cThe ID you entered is not a valid number!");
														
														return true;
														
													}
													
													if (jmp.getLeaveMessages().size() < Integer.parseInt(args[4]) + 1){
														
														ss(s, "The player &7" + jmp.getName() + " &7does not have the ID!");
														
													} else {
														
														String message = jmp.getLeaveMessages().get(Integer.parseInt(args[4]));												
														jmp.removeLeaveMessage(message);
														
														ss(s, new String[] {
																
																JM + "Removed the leave message for the player &7" + jmp.getName() + "&7:",
																translateMessage(s, message)
																
														});
														
													}
													
												}
												
											}
											
										} else {
											
											try {
												
												Integer.parseInt(args[2]);
												
											} catch (Exception e){
												
												ss(s, "&cThe ID you entered is not a valid number!");
												
												return true;
												
											}
											
											if (main.getUtils().getLeaveMessages().size() < Integer.parseInt(args[2]) + 1){
												
												ss(s, "ID not found!");
												
											} else {
												
												String message = main.getUtils().getLeaveMessages().get(Integer.parseInt(args[2]));												
												main.getUtils().removeLeaveMessage(message);
												
												ss(s, new String[] {
														
														JM + "Removed the leave message:",
														translateMessage(s, message)
														
												});
												
											}
											
										}
										
									}
									
								}
								
								break;
								
							case "list":
								
								if (args.length == 2){
									
									List<String> list = main.getUtils().getLeaveMessages();
									ss(s, "Global Leave Messages");
									
									for (int i = 0; i < list.size(); i++){
										
										ss(s, new String[]{
											
											"&7- &e" + i + " &6// " + translateMessage(s, list.get(i))
											
										});
										
									}
									
								} else {
									
									JMPlayer jmp = main.getUtils().getPlayer(args[2]);
									
									if (jmp == null){
										
										ss(s, "&cThat player has never joined!");
										
									} else {
										
										OfflinePlayer off = Bukkit.getOfflinePlayer(jmp.getName());
										List<String> list = jmp.getLeaveMessages();
										
										if (off.isOnline()){
											
											Player p = off.getPlayer();
											ss(s, p.getDisplayName() + "&7's Leave Messages");
											
											for (int i = 0; i < list.size(); i++){
												
												ss(s, new String[]{
													
													"&7- &e" + i + " &6// " + translateMessage(p, list.get(i))
													
												});
												
											}
											
										} else {
											
											ss(s, off.getName() + "&7's Leave Messages");
											
											for (int i = 0; i < list.size(); i++){
												
												ss(s, new String[]{
													
													"&7- &e" + i + " &6// " + translateMessage(off, list.get(i))
													
												});
												
											}
											
										}
										
									}
									
								}

								break;
								
							}
							
						}
						
						break;
						
					case "disable":
						
						if (checkPerm(s, "jm.disable")){
							
							main.getPM().disablePlugin(main);
							ss(s, "Successfully disabled JM. To enable it, you must reload your server.");
							
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
	
	private boolean checkPerm(CommandSender s, String perm){
		
		if (s.hasPermission(perm)){
			
			return true;
			
		} else {
			
			ss(s, "&cYou don't have permission for that!");
			
			return false;
			
		}
		
	}
	
}
