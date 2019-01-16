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
			meinEchoSocket = new Socket("192.168.43.38",1234);
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
	public void getSensor(){
		ask("Lookleft");
		if(ask("GetSensor").equals("2147483647")) {
			lefthouse = false;
		} else {
			lefthouse = true;
		}
		ask("Lookright");
		String s = ask("GetSensor");
		System.out.println(s);
		if(s.equals("2147483647")) {
			righthouse = false;
		} else {
			righthouse = true;
		}
		if(lefthouse && righthouse) {
			ask("Lookstraight");
			if(!(ask("GetSensor").equals("2147483647"))) {
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
		System.out.println("Bot: Linkshaus: "+this.lefthouse+" Rechtshaus: "+this.righthouse +" VBot: Linkshaus: "+vb.lefthouse+" Rechtshaus: "+vb.righthouse);
		if(vb.lefthouse == this.lefthouse && vb.righthouse == this.righthouse) return true;
		return false;


	}
}
