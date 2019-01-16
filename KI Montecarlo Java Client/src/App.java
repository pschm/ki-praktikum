import gui.GuiManager;
import localisation.Enviroment;
import svgReader.SVGReader;

public class App {


	public static void main(String[] args) {
		
		
		SVGReader svgReader = new SVGReader("/src/svgReader/3.2_Houses-1819.svg");
		if (svgReader == null) System.out.println("WARNING: Could not load svg");
		
        boolean[] upperRow = svgReader.getUpperRow();
        boolean[] underRow = svgReader.getUnderRow();
        
        Enviroment e = new Enviroment(upperRow, underRow, "10.0.1.9");
		GuiManager gm = new GuiManager(upperRow, underRow, e, e.getParticles());
		gm.update();
		
		e.localize();
		
		    
	}

}
