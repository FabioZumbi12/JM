package net.winneonsword.JM.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.winneonsword.JM.JM;
import net.winneonsword.JM.JMPlayer;

public class DataUtils {
	
	private JM main;
	
	private String message;
	private String playername;
	
	private List<String> users;
	private HashMap<String, JMPlayer> players;
	
	private List<String> join;
	private List<String> leave;
	
	public DataUtils(JM main){
		
		this.main = main;
		
		this.message = "e";
		this.playername = "7";
		
		this.users = new ArrayList<String>();
		this.players = new HashMap<String, JMPlayer>();
		
	}
	
	public String getMessageColour(){
		
		return message;
		
	}
	
	public String getPlayerColour(){
		
		return playername;
		
	}
	
	public List<String> getUsers(){
		
		return users;
		
	}
	
	public JMPlayer getPlayer(String name){
		
		if (players.containsKey(name)){
			
			return players.get(name);
			
		} else {
			
			main.getLogging().severe("There is no such player map for '" + name + "'!");
			
			return null;
			
		}
		
	}
	
	public List<String> getJoinMessages(){
		
		return join;
		
	}
	
	public List<String> getLeaveMessages(){
		
		return leave;
		
	}
	
	public void setMessageColour(String colour){
		
		message = colour;
		
	}
	
	public void setPlayerColour(String colour){
		
		playername = colour;
		
	}
	
	public void setUsers(List<String> users){
		
		this.users = users;
		
	}
	
	public void setJoinMessages(List<String> list){
		
		join = list;
		
	}
	
	public void setLeaveMessages(List<String> list){
		
		leave = list;
		
	}
	
	public void addPlayer(String name, JMPlayer p){
		
		if (players.containsKey(name)){
			
			main.getLogging().severe("The player '" + name + "' is already added!");
			
		} else {
			
			players.put(name, p);
			
		}
		
	}
	
	public void addUser(String name){
		
		users.add(name);
		
	}
	
	public void addJoinMessage(String message){
		
		join.add(message);
		
	}
	
	public void addLeaveMessage(String message){
		
		leave.add(message);
		
	}
	
	public void removeJoinMessage(String message){
		
		join.remove(message);
		
	}
	
	public void removeLeaveMessage(String message){
		
		leave.remove(message);
		
	}
	
	public void savePlayer(String name){
		
		JMPlayer p = getPlayer(name);
		
		main.getData().getConfig("config").set("player." + name + ".join", p.getJoinMessages());
		main.getData().getConfig("config").set("player." + name + ".leave", p.getLeaveMessages());
		
	}
	
	public void setupPlayer(String name){
		
		JMPlayer p = new JMPlayer(name);
		
		List<String> join = main.getData().getConfig("config").getStringList("player." + name + ".join");
		List<String> leave = main.getData().getConfig("config").getStringList("player." + name + ".leave");
		
		if (join.isEmpty()){
			
			join = new ArrayList<String>();
			
		}
		
		if (leave.isEmpty()){
			
			leave = new ArrayList<String>();
			
		}
		
		p.setJoinMessages(join);
		p.setLeaveMessages(leave);
		
		if (!(getUsers().contains(name))){
			
			addUser(name);
			
		}
		
		addPlayer(name, p);
		
	}
	
}
