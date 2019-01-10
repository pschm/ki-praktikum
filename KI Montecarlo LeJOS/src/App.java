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

import lejos.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.IOPort;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.port.TachoMotorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RangeScanner;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;


public class App {
	final static int RIGHT = 0;
	final static int RIGHT2 = 4;
	final static int STRAIGHT = 1;
	final static int LEFT = 2;
	final static int LEFT2 = 3;
	
	static RegulatedMotor m1 = Motor.D;
	
	static void turn(int direction) {
		switch(direction) {
		case LEFT: m1.rotate(1070);break;
		case LEFT2: m1.rotate(2*1070);break;
		case RIGHT: m1.rotate(-1070);break;
		case RIGHT2: m1.rotate(-2*1070);break;
		
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			
			EV3IRSensor distancesensor = new EV3IRSensor(SensorPort.S4);
			EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
			
			Wheel w1 = WheeledChassis.modelWheel(Motor.C, 3.47).offset(8.4);
			Wheel w2 = WheeledChassis.modelWheel(Motor.B, 3.47).offset(-8.4);
			Chassis c1 = new WheeledChassis(new Wheel[] {w1, w2},WheeledChassis.TYPE_DIFFERENTIAL);
			MovePilot movepilot = new MovePilot(c1);
			
			
			
			
			
			
			SampleProvider distance = distancesensor.getDistanceMode();
			float [] lastrange;
			
			int currentdirection = STRAIGHT;
			
			while(exit == 0) {
			      String anfrage; 
			      String antwort; 

			      anfrage = br.readLine(); 
			      antwort = "Antwort auf "+anfrage; 
			      if(anfrage.equals( "GetSensor" )) {
			    	  lastrange = new float[distance.sampleSize()];
			    	  distance.fetchSample(lastrange, 0);
			    	  antwort ="Color-ID=" +  color.getColorID() + " Distance = "+ (int)lastrange[0];
			    	  
			      }
			      if(anfrage.equals("exit")) {
			    	  exit=1;
			      }
			      if(anfrage.equals("Drive")) {
			    	  movepilot.setLinearSpeed(30);
			    	  movepilot.travel(-50);
			    	  
			      }
			      if(anfrage.equals("Lookleft")) {
			    	  if(currentdirection == RIGHT ) turn(LEFT2);
			    	  if(currentdirection == STRAIGHT) turn(LEFT);
			    	  currentdirection = LEFT;
			    	  
			      }
			      if(anfrage.equals("Lookstraight")) {
			    	 if(currentdirection == LEFT) turn(RIGHT);
			    	 if(currentdirection == RIGHT) turn(LEFT);
			    	 currentdirection = STRAIGHT;
			      }
			      if(anfrage.equals("Lookright")) {
			    	  if(currentdirection == LEFT ) turn(RIGHT2);
			    	  if(currentdirection == STRAIGHT) turn(RIGHT); 
			    	  currentdirection = RIGHT;
			      }
			      if(anfrage.matches("Turn.*")) {
			    	  
			    	  movepilot.rotate(Integer.parseInt(anfrage.substring(4)));
			    	  antwort = anfrage.substring(4);
			      }
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
