package tank;



public class App {

	public static void main(String[] args) {

		

			Driver dennis = new Driver();
			Navigator norbert = new Navigator();
			Gunlayer gustav = new Gunlayer();
			RadioOperator ralf = new RadioOperator(dennis, norbert, gustav);
			ralf.communicate();
			
	}

}