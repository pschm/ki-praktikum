

import localisation.Enviroment;

 



public class App {

	public static void main(String[] args) {
		Boolean[] oben = {true,true,false,true,false,false,true,false,true,false,false,true};
		Boolean[] unten = {true,false,true,true,false,true,false,false,true,false,true,true};
		Enviroment e = new Enviroment(oben, unten, "10.0.1.9");
		e.localize();      



	}

}
