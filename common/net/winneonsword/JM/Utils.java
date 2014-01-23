package net.winneonsword.JM;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	
	
	public static String JM;
	
	private static JM main;
	private static HashMap<String, String> consoleColours;
	
	public Utils(JM main){
		
		this.main = main;
		
		this.JM = "&7JM &e// &7";
		this.consoleColours = new HashMap<String, String>();
		
		consoleColours.put("0", "\u001B[0;30m");
		consoleColours.put("1", "\u001B[0;34m");
		consoleColours.put("2", "\u001B[0;32m");
		consoleColours.put("3", "\u001B[0;36m");
		consoleColours.put("4", "\u001B[0;31m");
		consoleColours.put("5", "\u001B[0;35m");
		consoleColours.put("6", "\u001B[0;33m");
		consoleColours.put("7", "\u001B[0;37m");
		consoleColours.put("8", "\u001B[1;30m");
		consoleColours.put("9", "\u001B[1;34m");
		consoleColours.put("a", "\u001B[1;32m");
		consoleColours.put("b", "\u001B[1;36m");
		consoleColours.put("c", "\u001B[1;31m");
		consoleColours.put("d", "\u001B[1;35m");
		consoleColours.put("e", "\u001B[1;33m");
		consoleColours.put("f", "\u001B[1;37m");
		
	}
	
	public static void s(Player p, String message){
		
		p.sendMessage(AS(JM + message));
		
	}
	
	public static void s(Player p, String[] message){
		
		p.sendMessage(AS(message));
		
	}
	
	public static void ss(CommandSender s, String message){
		
		s.sendMessage(AS(JM + message));
		
	}
	
	public static void ss(CommandSender s, String[] message){
		
		s.sendMessage(AS(message));
		
	}
	
	public static void b(String message){
		
		Bukkit.broadcastMessage(AS(message));
		
	}
	
	public static String AS(String message){
		
		return ChatColor.translateAlternateColorCodes('&', message);
		
	}
	
	public static String[] AS(String[] message){
		
		for (int i = 0; i < message.length; i++){
			
			message[i] = AS(message[i]);
			
		}
		
		return message;
		
	}
	
	public static String consoleAS(String message){
		
		for (String s : consoleColours.keySet()){
			
			message = message.replace("&" + s, consoleColours.get(s));
			
		}
		
		return message + consoleColours.get("7");
		
	}
	
	public static String[] consoleAS(String[] message){
		
		for (int i = 0; i < message.length; i++){
			
			message[i] = consoleAS(message[i]);
			
		}
		
		return message;
		
	}
	
	public static int random(int min, int max){
		
		int range = max - min + 1;
		
		return (int) (range * Math.random() + min);
		
	}
	
	public static void delay(Runnable run, int ticks){
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, run, ticks);
		
	}
	
	public static String translateMessage(Player p, String message){
		
		String mm = main.getUtils().getMessageColour();
		String pp = main.getUtils().getPlayerColour();
		
		return "&" + mm + message.replace("%p", "&" + pp + p.getDisplayName() + "&" + mm);
		
	}
	
	public static String translateMessage(OfflinePlayer p, String message){
		
		String mm = main.getUtils().getMessageColour();
		String pp = main.getUtils().getPlayerColour();
		
		return "&" + mm + message.replace("%p", "&" + pp + p.getName() + "&" + mm);
		
	}
	
	public static String translateMessage(CommandSender s, String message){
		
		String mm = main.getUtils().getMessageColour();
		String pp = main.getUtils().getPlayerColour();
		
		if (s instanceof Player){
			
			Player p = (Player) s;
			message = "&" + mm + message.replace("%p", "&" + pp + p.getDisplayName() + "&" + mm);
			
		} else {
			
			message = "&" + mm + message.replace("%p", "&" + pp + s.getName() + "&" + mm);
			
		}
		
		return message;
		
	}
	
	public static String stitchString(String[] array, int min){
		
		return stitchString(array, min, -1);
		
	}
	
	public static String stitchString(String[] array, int min, int max){
		
		min--;
		
		if (max == -1){
			
			max = array.length;
			
		} else {
			
			max++;
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = min; i < max; i++){
			
			sb.append(" " + array[i]);
			
		}
		
		return sb.toString().replaceFirst(" ", "");
		
	}
	
}
