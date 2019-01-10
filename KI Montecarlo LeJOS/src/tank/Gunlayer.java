package tank;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class Gunlayer {
	final static int RIGHT = 0;
	final static int RIGHT2 = 4;
	final static int STRAIGHT = 1;
	final static int LEFT = 2;
	final static int LEFT2 = 3;
	
	static RegulatedMotor m1 = Motor.A;
	
	int currentdirection = STRAIGHT;
	
	private void turn(int direction) {
		switch(direction) {
		case LEFT: m1.rotate(1070);break;
		case LEFT2: m1.rotate(2*1070);break;
		case RIGHT: m1.rotate(-1070);break;
		case RIGHT2: m1.rotate(-2*1070);break;
		
		}
		
	}
	
	public void lookleft() {
		if(currentdirection == RIGHT ) turn(LEFT2);
  	  if(currentdirection == STRAIGHT) turn(LEFT);
  	  currentdirection = LEFT;
	}
	
	public void lookright() {
		if(currentdirection == LEFT ) turn(RIGHT2);
  	  if(currentdirection == STRAIGHT) turn(RIGHT); 
  	  currentdirection = RIGHT;
		
	}
	
	public void lookstraight() {
		if(currentdirection == LEFT) turn(RIGHT);
   	 if(currentdirection == RIGHT) turn(LEFT);
   	 currentdirection = STRAIGHT;
	}
	
	
}
