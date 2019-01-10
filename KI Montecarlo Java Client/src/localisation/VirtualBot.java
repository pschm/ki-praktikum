package localisation;

public class VirtualBot extends Bot {
	
	final static int LEFT = 0; //von Tür zu Fenster
	final static int RIGHT = 1; //von Fenster zu Tür
	
	
	private int position; //links = 0 11 = rechts
	private int direction; 
	
	boolean houseleft ,houseright;
	
	Enviroment e;
	
	public VirtualBot(Enviroment e){
		this.e =e;
	}

	@Override
	public void Drive(){
		if (direction == LEFT) {
			position -= 1;
		}
		if (direction == RIGHT) {
			position +=1;
		}
		//Prüfen ob er noch existiert
	}
	

	@Override
	public void GetSensor(){
		if (direction == LEFT){
			houseleft = e.ishouse(position,Enviroment.DOWN);
			houseright = e.ishouse(position, Enviroment.UP);
		}
		if (direction == RIGHT) {
			houseleft = e.ishouse(position,Enviroment.UP);
			houseright = e.ishouse(position, Enviroment.DOWN);
		}
			
	}
}
