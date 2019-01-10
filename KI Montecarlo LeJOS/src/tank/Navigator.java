package tank;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class Navigator {
	EV3IRSensor distancesensor = new EV3IRSensor(SensorPort.S4);
	EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
	
	SampleProvider distance = distancesensor.getDistanceMode();
	float [] lastrange;
	
	public int  getDistance() {
  	  lastrange = new float[distance.sampleSize()];
  	  distance.fetchSample(lastrange, 0);
  	  return (int)lastrange[0];
	}
	
	public int getGroundColor() {
		return color.getColorID();
	}
}
