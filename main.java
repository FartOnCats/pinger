
/**
 * @author FartOnCats
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class main {
	public static void clrscr(){
		//https://stackoverflow.com/questions/2979383/java-clear-the-console/33379766#33379766
			
			
	    //Clears Screen in java
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	        	//for windows
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	        //for linux
	    } catch (IOException | InterruptedException ex) {}
	}

	
	
	
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        BufferedReader reader;
		clrscr();
        ArrayList addresses = new ArrayList();
        try {
			reader = new BufferedReader(new FileReader(System.getenv("appdata") + "/ping.txt"));
			String line = reader.readLine();
			int linecount = 0 ;
			while (line != null) {
				String[] ip = line.split(":");
				addresses.add(ip[1]);
				line = reader.readLine();
				linecount++;
			}
			reader.close();
			while (true) {
				for(int i = 0; i < addresses.size(); i++) {
					//https://stackoverflow.com/questions/11506321/how-to-ping-an-ip-address
					InetAddress inet = InetAddress.getByName((String) addresses.get(i));
					String response;
					if(inet.isReachable(5000)) {
						response = "[REACHABLE]";
					}else {
						response = 	"-[**UNREACHABLE**]-";
					}
					System.out.println((String) addresses.get(i) + " : " + response + "\n");
				}
				TimeUnit.SECONDS.sleep(linecount * 3);
				clrscr();
			
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
