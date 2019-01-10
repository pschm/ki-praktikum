package tank;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Driver {
	Wheel w1 = WheeledChassis.modelWheel(Motor.C, 3.47).offset(8.4);
	Wheel w2 = WheeledChassis.modelWheel(Motor.B, 3.47).offset(-8.4);
	Chassis c1 = new WheeledChassis(new Wheel[] {w1, w2},WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot movepilot = new MovePilot(c1);
	
	public void drive() {
		drive(-50);
	}
	
	public void drive(int distance) {
		movepilot.setLinearSpeed(30);
  	    movepilot.travel(distance);
	}
	
	public void turn(int degree) {
		movepilot.rotate(degree);
  	  return;
	}
	

}
