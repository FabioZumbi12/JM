package net.winneonsword.JM;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.winneonsword.JM.utils.ConfigUtils;
import net.winneonsword.JM.utils.LogUtils;

public class JM extends JavaPlugin {
	
	private PluginManager pm;
	
	private LogUtils logUtils;
	private ConfigUtils configUtils;
	
	private String[] configs;
	
	@Override
	public void onEnable(){
		
		new Utils();
		
		pm = getServer().getPluginManager();
		logUtils = new LogUtils(this);
		configUtils = new ConfigUtils(this);
		
		this.configs = new String[] {
				
				"config"
				
		};
		
		for (String c : configs){
			
			getData().registerFile(c);
			
		}
		
		getCommand("jm").setExecutor(new CommandJm(this));
		getLogging().info("JoinMessages has been enabled.");
		
	}
	
	@Override
	public void onDisable(){
		
		for (String c : configs){
			
			getData().saveConfig(c);
			getLogging().info('e', "Saved the config '" + c + "'.");
			
		}
		
		getLogging().info('c', "JoinMessages has been disabled.");
		
	}
	
	public PluginManager getPM(){
		
		return pm;
		
	}
	
	public ConfigUtils getData(){
		
		return configUtils;
		
	}
	
	public LogUtils getLogging(){
		
		return logUtils;
		
	}
	
}
