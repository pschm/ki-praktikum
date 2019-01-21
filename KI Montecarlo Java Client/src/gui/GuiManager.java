package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import localisation.Enviroment;
import localisation.VirtualBot;

public class GuiManager {

	private MapPanel map;
	private JFrame mapFrame;
	private JFrame controlFrame;
	private BufferedImage groupIcon;
	private JLabel botAction;
	
	private Enviroment environment;
	
	public GuiManager(boolean[] north, boolean[] south, Enviroment e, ArrayList<VirtualBot> bots) {
		this.environment = e;
		
		map = new MapPanel(north, south, bots);
		
		loadIcon();
		buildGui();
	}
	
	public void buildGui() {
		// initialize frame
		mapFrame = new JFrame();
		controlFrame = new JFrame();
		
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setSize(1600, 500);
		mapFrame.setLocation(0, 0);
		mapFrame.setResizable(true);
		mapFrame.setVisible(true);
		mapFrame.setTitle("AGrün/BRot - Monte Carlo Localization");
		mapFrame.setIconImage(groupIcon);

		controlFrame.setSize(320, 500);
		controlFrame.setLocation(1600, 0);
		controlFrame.setVisible(true);
		controlFrame.setResizable(true);
		controlFrame.setTitle("AGrün/BRot - Controlpanel");
		controlFrame.setIconImage(groupIcon);
		
		// start button
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 21));
		Color c = new Color(75,75,75);
		btnStart.setForeground(Color.LIGHT_GRAY);
		btnStart.setBackground(c);		
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO start bot
				System.out.println("Bot started");
				environment.localize();
			}
		});
		
		// info label
		botAction = new JLabel();
		botAction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 21));
		botAction.setOpaque(true);
		botAction.setBackground(Color.DARK_GRAY);
		botAction.setForeground(Color.LIGHT_GRAY);
		updateBotAction("STANDBY");
		
		// add components mapFrame
		mapFrame.add(map);
		
		// add components controlFrame
		controlFrame.setLayout(new GridLayout(0, 1));
		controlFrame.setBackground(Color.DARK_GRAY);
		controlFrame.revalidate();
		controlFrame.repaint();
		controlFrame.add(btnStart);
		controlFrame.add(botAction);
		controlFrame.add(getFillerPanel());
		controlFrame.add(getFillerPanel());
		controlFrame.add(getFillerPanel());
		controlFrame.add(getFillerPanel());
	}
	
	/**
	 * Update the botAction in the control-frame
	 * @param botAction
	 */
	public void updateBotAction(String botAction) {
		this.botAction.setText("  Botaction: " + botAction);
	}
	
	private void loadIcon() {
		try {                
			groupIcon   = ImageIO.read(new File("pictures/title.png"));
		} catch (IOException ex) {
			System.out.println("WARNING: Could not load title icon!");
			// maybe some backup-stuff
		}
	}
	
	public synchronized void update(ArrayList<VirtualBot> vbs) {
		
		map.setVirtualBots(vbs);
		mapFrame.revalidate();
		map.revalidate();
		mapFrame.repaint();
		map.repaint();
		System.out.println("Wir haben geupdaatet");
		
	}
	
	private JPanel getFillerPanel() {
		JPanel p = new JPanel();
		p.setBackground(Color.DARK_GRAY);
		return p;
	}
}
