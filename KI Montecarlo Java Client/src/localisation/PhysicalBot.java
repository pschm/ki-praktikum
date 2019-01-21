package localisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class PhysicalBot extends Bot {
	final static String MAXDISTANCE ="2147483647";
	
	private Socket meinEchoSocket;
	private OutputStream socketoutstr; 
	private OutputStreamWriter osr;
	private BufferedWriter bw;
	InputStream socketinstr;
	InputStreamReader isr;
	BufferedReader br;
	Enviroment e;


	public PhysicalBot (Enviroment e, String ipadress) {
		this.e = e;
		try {
			meinEchoSocket = new Socket(ipadress,1234);
			socketoutstr = meinEchoSocket.getOutputStream(); 
			osr = new OutputStreamWriter( socketoutstr ); 
			bw = new BufferedWriter( osr );
			socketinstr = meinEchoSocket.getInputStream(); 
			isr = new InputStreamReader( socketinstr ); 
			br = new BufferedReader( isr ); 
			String anfrage = "Hallo"; 

			bw.write(anfrage); 
			bw.newLine(); 
			bw.flush(); 
			br.readLine(); 
			
			ask("Align");


		} catch (UnknownHostException ex) {
			
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 





	}
	private String ask(String s) {

		try {
			bw.write(s);
			bw.newLine();
			bw.flush(); 
			return br.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		} 
		return "";

	}


	@Override
	public boolean drive(){
		ask("Drive");
		return true;

	}

	@Override
	public void updateSensor(){
		ask("Lookleft");
		if(ask("GetSensor").equals(MAXDISTANCE)) {
			lefthouse = false;
		} else {
			lefthouse = true;
		}
		ask("Lookright");
		if(ask("GetSensor").equals(MAXDISTANCE)) {
			righthouse = false;
		} else {
			righthouse = true;
		}
		//wenn er am Ende ist dann alls umdrehen
		if(lefthouse && righthouse) {
			ask("Lookstraight");
			if(!(ask("GetSensor").equals(MAXDISTANCE))) {
				turn();
				e.turnAll(); 
			}
		}
		ask("Lookstraight");
	}

	@Override
	public void turn() {
		ask("Turn180");

	}

	public boolean equals(VirtualBot vb) {
		//System.out.println("Bot: Linkshaus: "+this.lefthouse+" Rechtshaus: "+this.righthouse +" VBot: Linkshaus: "+vb.lefthouse+" Rechtshaus: "+vb.righthouse);
		if(vb.lefthouse == this.lefthouse && vb.righthouse == this.righthouse) return true;
		return false;


	}
}
