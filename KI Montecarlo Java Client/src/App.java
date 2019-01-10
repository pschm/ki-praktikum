import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
 



public class App {

	public static void main(String[] args) {
		try { 
		      String host = "10.0.1.46"; 
		      Socket meinEchoSocket = new Socket(host,1234); 

		      OutputStream socketoutstr = meinEchoSocket.getOutputStream(); 
		      OutputStreamWriter osr = new OutputStreamWriter( socketoutstr ); 
		      BufferedWriter bw = new BufferedWriter( osr ); 

		      InputStream socketinstr = meinEchoSocket.getInputStream(); 
		      InputStreamReader isr = new InputStreamReader( socketinstr ); 
		      BufferedReader br = new BufferedReader( isr ); 

		      String anfrage = "Hallo"; 
		      String antwort; 

		      bw.write(anfrage); 
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine(); 

		      //System.out.println("Host = "+host); 
		      //System.out.println("Echo = "+antwort); 
		      
		      bw.write("GetSensor");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine(); 
		      System.out.println(antwort);
		      
		      bw.write("Drive");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      
		      bw.write("GetSensor");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine(); 
		      System.out.println(antwort);
		      
		      bw.write("Turn90");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      
		      bw.write("Turn-270");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      
		      bw.write("Drive");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      bw.write("GetSensor");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine(); 
		      System.out.println(antwort);
		      
		      bw.write("Turn180");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      
		      bw.write("GetSensor");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine(); 
		      System.out.println(antwort);
		      
		      bw.write("exit");
		      bw.newLine(); 
		      bw.flush(); 
		      antwort = br.readLine();
		      
		      
		      bw.close(); 
		      br.close(); 
		      meinEchoSocket.close(); 
		    } 
		    catch (UnknownHostException uhe) { 
		      //System.out.println(uhe); 
		    } 
		    catch (IOException ioe) { 
		      //System.out.println(ioe); 
		    } 



	}

}
