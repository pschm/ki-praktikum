package tank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class RadioOperator {
	Driver d;
	Navigator n;
	Gunlayer g;

	RadioOperator (Driver d, Navigator n, Gunlayer g){
		this.d = d;
		this.n = n;
		this.g = g;
	}


	public void communicate() {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket clientSoocket = serverSocket.accept();

			OutputStream socketoutstr = clientSoocket.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(socketoutstr);
			BufferedWriter bw = new BufferedWriter ( osr);

			InputStream socketinstr = clientSoocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(socketinstr);
			BufferedReader br = new BufferedReader(isr);

			int exit = 0;
			while(exit == 0) {
				String anfrage; 
				String antwort; 

				anfrage = br.readLine(); 
				antwort = "Antwort auf "+anfrage; 
				if(anfrage.equals( "GetSensor" )) {
					//antwort = "Color-ID=" +  n.getGroundColor() + " Distance = "+ n.getDistance();
					antwort = ""+n.getDistance();

				} else 
				if(anfrage.equals("exit")) {
					exit=1;
				} else 
				if(anfrage.equals("Drive")) {
					d.drive();

				} else 
				if(anfrage.equals("Lookleft")) {
					g.lookleft();		    	  
				} else 
				if(anfrage.equals("Lookstraight")) {
					g.lookstraight();
				} else 
				if(anfrage.equals("Lookright")) {
					g.lookright();
				} else 
				if(anfrage.matches("Turn.*")) {

					d.turn(Integer.parseInt(anfrage.substring(4)));
				} else
				if(anfrage.matches("Drive.*")) {

					d.drive((Integer.parseInt(anfrage.substring(5))));
				} else
				if(anfrage.equals("Align")) {
					n.align();
				} else
				bw.write(antwort); 
				bw.newLine(); 
				bw.flush(); 

			}

			clientSoocket.close();
			serverSocket.close();
		}
		catch (UnknownHostException uhe) { 
			System.out.println(uhe); 
		} 
		catch (IOException ioe) { 
			System.out.println(ioe); 
		} 
	}

}
