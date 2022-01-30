package airteam.projects.atarilogo.utilities;

public class Log_Utilies {
	public static void logInfo(Object... messages) {
		for(Object msg : messages) {
			System.out.println("INFO : " + String.valueOf(msg));
		}
	}
	
	public static void logError(Object... messages) {
		for(Object msg : messages) {
			System.out.println("ERROR : " + String.valueOf(msg));
		}
	}
	
}
