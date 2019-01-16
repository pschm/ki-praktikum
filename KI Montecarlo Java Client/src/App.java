

import localisation.Enviroment;

 



public class App {

	public static void main(String[] args) {
		boolean[] oben = {true,true,false,true,false,false,true,false,true,false,false,true};
		boolean[] unten = {true,false,true,true,false,true,false,false,true,false,true,true};
		Enviroment e = new Enviroment(oben, unten, "192.168.43.38");
		e.localize();      



	}

}
