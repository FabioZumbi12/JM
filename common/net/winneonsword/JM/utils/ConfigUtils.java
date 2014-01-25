package net.winneonsword.JM.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

import net.winneonsword.JM.JM;

public class ConfigUtils {
	
	private JM main;
	private HashMap<String, File> configs;
	
	public ConfigUtils(JM main){
		
		this.main = main;
		this.configs = new HashMap<String, File>();
		
	}
	
	public boolean registerFile(String name){
		
		if (!(main.getDataFolder().exists())){
			
			main.getDataFolder().mkdirs();
			
		}
		
		if (configs.containsKey(name)){
			
			main.getLogging().severe("The config '" + name + "' already is registered!");
			
			return false;
			
		} else {
			
			File file = new File(main.getDataFolder(), name + ".yml");
			
			if (!(file.exists())){
				
				copy(main.getResource(name + ".yml"), file);
				
			}
			
			configs.put(name, file);
			
			return true;
			
		}
		
	}
	
	public File getFile(String name){
		
		if (configs.containsKey(name)){
			
			return configs.get(name);
			
		} else {
			
			main.getLogging().severe("The file '" + name + "' is not registered!");
			
			return null;
			
		}
		
	}
	
	public YamlConfiguration getConfig(String name){
		
		File file = getFile(name);
		
		if (file == null){
			
			return null;
			
		} else {
			
			return YamlConfiguration.loadConfiguration(file);
			
		}
		
	}
	
	public boolean saveConfig(String name){
		
		try {
			
			Field field = main.getClass().getDeclaredField("config");
			field.setAccessible(true);
			
			YamlConfiguration config = (YamlConfiguration) field.get(main);
			
			if (config == null){
				
				return false;
				
			} else {
				
				config.save(getFile(name));
				
				return true;
				
			}
			
		} catch (Exception e){
			
			main.getLogging().severe("Failed to save the config '" + name + "'.");
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public void copy(InputStream in, File file){
		
		try {
			
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			
			while ((len = in.read(buf)) > 0){
				
				out.write(buf, 0, len);
				
			}
			
			out.close();
			in.close();
			
		} catch (Exception e){
			
			main.getLogging().severe("Failed to copy the file '" + file.getName() + "'!");
			e.printStackTrace();
			
		}
		
	}
	
}
