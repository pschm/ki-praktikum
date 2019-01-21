import gui.GuiManager;
import localisation.Enviroment;
import svgReader.SVGReader;

public class App {

	public static void main(String[] args) {
		
		SVGReader svgReader = new SVGReader("src/svgReader/3.2_Houses-1819.svg");		
        boolean[] upperRow = svgReader.getUpperRow();
        boolean[] underRow = svgReader.getUnderRow();
        Enviroment e = new Enviroment(upperRow, underRow, "10.0.1.2");
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				GuiManager gm = new GuiManager(upperRow, underRow, e, e.getParticles());
				e.setGuiManager(gm);
				gm.update(e.getParticles());
				
			}
		}).start(); 
        
		if (upperRow == null || underRow == null) {
			System.out.println("WARNING: Could not load svg");
			return;
		}
        e.localize();
		
				    
	}
}
