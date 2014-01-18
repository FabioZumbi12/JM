package net.winneonsword.JM;

import java.util.List;

public class JMPlayer {
	
	private String name;
	
	private List<String> join;
	private List<String> leave;
	
	public JMPlayer(String name){
		
		this.name = name;
		
	}
	
	public boolean hasJoinMessages(){
		
		if (join.isEmpty()){
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	public boolean hasLeaveMessages(){
		
		if (leave.isEmpty()){
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public List<String> getJoinMessages(){
		
		return join;
		
	}
	
	public List<String> getLeaveMessages(){
		
		return leave;
		
	}
	
	public void setJoinMessages(List<String> list){
		
		join = list;
		
	}
	
	public void setLeaveMessages(List<String> list){
		
		leave = list;
		
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
	
}
