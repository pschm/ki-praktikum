package tank;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class Navigator {
	private Driver driver;
	private Gunlayer gunlayer;
	
	EV3IRSensor distancesensor = new EV3IRSensor(SensorPort.S4);
	EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
	
	SampleProvider distance = distancesensor.getDistanceMode();
	float [] lastrange;
	
	public Navigator(Driver dr,Gunlayer gu) {
		driver=dr;
		gunlayer=gu;
	}
	
	public int  getDistance() {
  	  lastrange = new float[distance.sampleSize()];
  	  distance.fetchSample(lastrange, 0);
  	  return (int)lastrange[0];
	}
	
	public int getGroundColor() {
		return color.getColorID();
	}
	
	public void align() {
		gunlayer.lookleft();
		int distance = getDistance();
		boolean waslefthouse;
		if(distance < 100) {
			waslefthouse = true;
		} else {
			waslefthouse = false;
		}
		boolean islefthouse = waslefthouse;
		int wallcounter = 5; 
		while(islefthouse == waslefthouse) {
			driver.drive(-5);
			gunlayer.lookleft();
			distance = getDistance();
			if(distance < 100) {
				islefthouse = true;
			} else {
				islefthouse = false;
			}
			wallcounter++;
			if(wallcounter>5) {
				gunlayer.lookstraight();
				distance = getDistance();
				if(distance < 60) {
					driver.turn(180);
				}
				wallcounter = 0;
			}
		}
		driver.drive(-15);
		gunlayer.lookstraight();
	}
}
