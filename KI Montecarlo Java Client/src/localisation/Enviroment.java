package localisation;

import java.util.ArrayList;

public class Enviroment {
	
	public final static int DOWN = 0;
	public final static int UP = 1;

	private boolean[] oben;
	private boolean[] unten;
	private int maxlenght;
	
	ArrayList<VirtualBot> particles = new ArrayList<VirtualBot>();
	PhysicalBot tank;
	
	public Enviroment (boolean[] oben, boolean[] unten,String botip) {
		this.oben = oben;
		this.unten = unten;
		tank = new PhysicalBot(this, botip);
		maxlenght = oben.length;
		for( int x = 0; x < maxlenght;x++) {
			particles.add(new VirtualBot(this, x, VirtualBot.LEFT));
			particles.add(new VirtualBot(this, x, VirtualBot.RIGHT));
			
		}
	}
	
	public ArrayList<VirtualBot> getParticles() {
		return particles;
	}
	
	public void localize() {
		while(particles.size()>1) {
			tank.getSensor();
			//Kopie der Liste um Löschen zu ermöglichen
			ArrayList<VirtualBot> newParticles = new ArrayList<VirtualBot>();
			for(VirtualBot b : particles) {
				System.out.println("Check");
				b.getSensor();
				if(!(tank.equals(b))) {
					System.out.println("nicht gleich");
				} else {
					newParticles.add(b);
				}
				
				
			}
			//
			particles = newParticles;
			newParticles = new ArrayList<VirtualBot>();
			for(VirtualBot b : particles) {
				if( b.drive()) newParticles.add(b);
			}
			particles = newParticles;
			for(VirtualBot b : particles) {
				b.getSensor();
			}
			System.out.println("Vbots= "+ particles.size());
			tank.drive();
			
		}
		System.out.println("Position gefunden: "+particles.get(0).getPosition()+" Richtung: "+particles.get(0).getDirection());
	}
	
	public boolean ishouse( int x, int direction) {
		if(direction == UP) return oben[x];
		if(direction == DOWN) return unten[x];
		return false;
		
	}
	
	public int getMaxlenght() {
		return maxlenght;
	}
	
	/*public void removeBot (VirtualBot b) {
		System.out.println("Entfernen");
		Particles.remove(b);
	}*/
	
	public void turnAll() {
		for(Bot b : particles) {
			b.turn();
		}
	}
}
