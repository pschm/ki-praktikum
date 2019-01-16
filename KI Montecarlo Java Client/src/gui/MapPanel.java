package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import localisation.Bot;
import localisation.VirtualBot;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	
	public static final int STREETSIZE = 12;
	
	private boolean[] north;
	private boolean[] south;

	private int xStepSize;
	private int yStepSize;

	private ArrayList<VirtualBot> bots;
	
	private BufferedImage imgMeadow;
	private BufferedImage imgHouse;
	private BufferedImage imgRoad;
	private BufferedImage imgBotLeft;
	private BufferedImage imgBotRight;
	
	private Image scaledHouse;
	private Image scaledRoad;
	private Image scaledBotLeft;
	private Image scaledBotRight;
	
	/**
	 * @param north northern street view (based on svg)
	 * @param south southern street view (based on svg)
	 */
	public MapPanel(boolean[] north, boolean[] south, ArrayList<VirtualBot> bots) {
		this.north = north;
		this.south = south;
		this.bots  = bots;
	
		setBackground(Color.DARK_GRAY);
		loadImages();
	}
	
	private void loadImages() {
		try {                
			imgMeadow   = ImageIO.read(new File("pictures/meadow.png"));
			imgHouse    = ImageIO.read(new File("pictures/house2.png"));
			imgRoad     = ImageIO.read(new File("pictures/road2.png"));
			imgBotLeft  = ImageIO.read(new File("pictures/bot_left.png"));
			imgBotRight = ImageIO.read(new File("pictures/bot_right.png"));
		} catch (IOException ex) {
			System.out.println("WARNING: Could not load pictures!");
			// maybe dome backup-stuff
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		xStepSize = getWidth() / STREETSIZE;
		yStepSize = getHeight() / 3;
		
		scaledRoad = imgRoad.getScaledInstance(xStepSize, (int) (0.75*yStepSize), 1);
		scaledHouse = imgHouse.getScaledInstance(xStepSize, yStepSize, 1);
		scaledBotLeft = imgBotLeft.getScaledInstance(xStepSize / 2, yStepSize / 2, 1);
		scaledBotRight = imgBotRight.getScaledInstance(xStepSize / 2, yStepSize / 2, 1);

		//background
//		g2d.drawImage(imgMeadow, 0, 0, this);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		printMap(g2d);
		printBots(g2d);
	}
	
	public void printMap(Graphics2D g2d) {
		//System.out.println(xStepSize + " / " + yStepSize);
		
		//g2d.drawRect(0, 0, getWidth(), (int) (0.75*getHeight()) ); // umrandung
		
		// draw house (north-side)
		g2d.setColor(Color.DARK_GRAY);
		for (int i = 0; i < STREETSIZE; i++)
			if (north[i]) g2d.drawImage(scaledHouse, i*xStepSize, 0, this); //g2d.fillRect(i*xStepSize, 0, xStepSize, yStepSize );
//		if (north[i]) g2d.fillRect(i*xStepSize, 0, xStepSize, yStepSize );
		
		// draw street
		g2d.setColor(Color.RED);
		for (int i = 0; i < STREETSIZE + 1; i++)	
			g2d.drawImage(scaledRoad, i*xStepSize, (int) (1.125*yStepSize), this);//g2d.fillRect(i*xStepSize, (int) (1.25*yStepSize), xStepSize, yStepSize/2 );
		
		// draw house (south-side)
		g2d.setColor(Color.DARK_GRAY);
		for (int i = 0; i < STREETSIZE; i++) 
			if (south[i]) g2d.drawImage(scaledHouse, i*xStepSize, 2*yStepSize, this ); //if (south[i]) g2d.fillRect(i*xStepSize, 2*yStepSize, xStepSize, yStepSize );

	}
	
	public void printBots(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		for (VirtualBot b : bots) {
			//g2d.fillRect(b.getPosition()*xStepSize, (int) (1.25*yStepSize), xStepSize/2, yStepSize/2 );
			//TODO : auf statische werte (?) zugreifen !
			if (b.getDirection() == 1) {
				g2d.drawImage(scaledBotRight, b.getPosition()*xStepSize, (int) (1.25*yStepSize), this);
			} else {
				g2d.drawImage(scaledBotLeft, b.getPosition()*xStepSize, (int) (1.25*yStepSize), this); // bot nach links
			}
		}
	}
	
}
