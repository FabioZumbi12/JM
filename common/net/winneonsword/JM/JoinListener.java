package net.winneonsword.JM;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.winneonsword.JM.Utils.*;

public class JoinListener implements Listener {
	
	private JM main;
	
	public JoinListener(JM main){
		
		this.main = main;
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e){
		
		final Player p = e.getPlayer();
		
		if (!(p.hasPlayedBefore())){
			
			main.getUtils().setupPlayer(p.getName());
			
		}
		
		JMPlayer jmp = main.getUtils().getPlayer(p.getName());
		e.setJoinMessage(null);
		
		final String message = main.getUtils().getMessageColour();
		final String playername = main.getUtils().getPlayerColour();
		String m = null;
		
		if (jmp.hasJoinMessages()){
			
			List<String> list = jmp.getJoinMessages();
			int rand = random(0, list.size() - 1);
			m = list.get(rand);
			
		} else {
			
			List<String> list = main.getData().getConfig("config").getStringList("global.join");
			int rand = random(0, list.size() - 1);
			m = list.get(rand);
			
		}
		
		final String mm = m;
		
		delay(main, new Runnable(){
			
			public void run(){
				
				b("&" + message + mm.replace("%p", "&" + playername + p.getDisplayName() + "&" + message));
				
			}
			
		}, 10);
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onLeave(PlayerQuitEvent e){
		
final Player p = e.getPlayer();
		
		if (!(p.hasPlayedBefore())){
			
			main.getUtils().setupPlayer(p.getName());
			
		}
		
		JMPlayer jmp = main.getUtils().getPlayer(p.getName());
		e.setQuitMessage(null);
		
		final String message = main.getUtils().getMessageColour();
		final String playername = main.getUtils().getPlayerColour();
		String m = null;
		
		if (jmp.hasLeaveMessages()){
			
			List<String> list = jmp.getLeaveMessages();
			int rand = random(0, list.size() - 1);
			m = list.get(rand);
			
		} else {
			
			List<String> list = main.getData().getConfig("config").getStringList("global.leave");
			int rand = random(0, list.size() - 1);
			m = list.get(rand);
			
		}
		
		final String mm = m;
		
		delay(main, new Runnable(){
			
			public void run(){
				
				b("&" + message + mm.replace("%p", "&" + playername + p.getDisplayName() + "&" + message));
				
			}
			
		}, 10);
		
	}
	
}
