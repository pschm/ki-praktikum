package localisation;

import java.util.ArrayList;

public class Enviroment {
	
	public final static int DOWN = 0;
	public final static int UP = 1;

	private Boolean[] oben;
	private Boolean[] unten;
	private int maxlenght;
	
	ArrayList<VirtualBot> Particles = new ArrayList<VirtualBot>();
	PhysicalBot tank;
	
	public Enviroment (Boolean[] oben, Boolean[] unten,String botip) {
		this.oben = oben;
		this.unten = unten;
		tank = new PhysicalBot(this, botip);
		maxlenght = oben.length;
		for( int x = 0; x < maxlenght;x++) {
			Particles.add(new VirtualBot(this, x, VirtualBot.LEFT));
			Particles.add(new VirtualBot(this, x, VirtualBot.RIGHT));
			
		}
	}
	
	public void localize() {
		while(Particles.size()>1) {
			tank.getSensor();
			
			ArrayList<VirtualBot> newParticles = new ArrayList<VirtualBot>();
			for(VirtualBot b : Particles) {
				System.out.println("Check");
				b.getSensor();
				if(!(tank.equals(b))) {
					System.out.println("nicht gleich");
				} else {
					newParticles.add(b);
				}
				
				
			}
			Particles = newParticles;
			for(VirtualBot b : newParticles) {
				b.drive();
			}
			newParticles = Particles;
			for(VirtualBot b : newParticles) {
				b.getSensor();
			}
			System.out.println("Vbots= "+ Particles.size());
			tank.drive();
			
		}
		System.out.println("Position gefunden: "+Particles.get(0).getPosition()+" Richtung: "+Particles.get(0).getDirection());
	}
	
	public boolean ishouse( int x, int direction) {
		if(direction == UP) return oben[x];
		if(direction == DOWN) return unten[x];
		return false;
		
	}
	
	public int getMaxlenght() {
		return maxlenght;
	}
	
	public void removeBot (VirtualBot b) {
		System.out.println("Entfernen");
		Particles.remove(b);
	}
	
	public void turnAll() {
		for(Bot b : Particles) {
			b.turn();
		}
	}
}
