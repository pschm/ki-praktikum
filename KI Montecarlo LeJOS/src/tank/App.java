package tank;



public class App {

	public static void main(String[] args) {

		

			Driver dennis = new Driver();
			Gunlayer gustav = new Gunlayer();
			Navigator norbert = new Navigator(dennis,gustav);
			RadioOperator ralf = new RadioOperator(dennis, norbert, gustav);
			ralf.communicate();
			
	}

}