package net.winneonsword.JM.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.winneonsword.JM.JM;

import static net.winneonsword.JM.Utils.*;

public class LogUtils {
	
	private Logger logger;
	
	public LogUtils(JM main){
		
		this.logger = main.getLogger();
		
	}
	
	public void info(String message){
		
		logger.log(Level.INFO, consoleAS("&a" + message));
		
		
	}
	
	public void info(char colour, String message){
		
		logger.log(Level.INFO, consoleAS("&" + colour + message));
		
	}
	
	public void severe(String message){
		
		logger.log(Level.SEVERE, consoleAS("&c" + message));
		
	}
	
}
