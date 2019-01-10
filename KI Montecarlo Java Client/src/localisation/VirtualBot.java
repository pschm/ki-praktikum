package localisation;

public class VirtualBot extends Bot {
	
	public final static int LEFT = 0; //von Tür zu Fenster
	public final static int RIGHT = 1; //von Fenster zu Tür
	
	
	private int position; //links = 0 11 = rechts
	private int direction; 
	
	Enviroment e;
	
	public VirtualBot(Enviroment e,int position,int direction){
		this.e =e;
		this.position = position;
		this.direction = direction;
	}

	@Override
	public void drive(){
		if (direction == LEFT) {
			position -= 1;
		}
		if (direction == RIGHT) {
			position +=1;
		}
		if (position<0 ) {
			e.removeBot(this);
		}
		if(position >= e.getMaxlenght()){
			e.removeBot(this);
		}
	}
	@Override
	public void turn() {
		if (direction == LEFT) direction = RIGHT;
		if (direction == RIGHT) direction = LEFT;
	}
	

	@Override
	public void getSensor(){
		if (direction == LEFT){
			lefthouse = e.ishouse(position,Enviroment.DOWN);
			righthouse = e.ishouse(position, Enviroment.UP);
		}
		if (direction == RIGHT) {
			lefthouse = e.ishouse(position,Enviroment.UP);
			righthouse = e.ishouse(position, Enviroment.DOWN);
		}
			
	}
	
	public int getPosition() {
		return position;
	}
	public int getDirection() {
		return direction;
	}
}
