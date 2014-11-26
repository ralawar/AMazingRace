package Main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long calcTime = 1000 / FPS;
	
	private Graphics2D g;
	
	private GameStateManager gsm;

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void run() {
	}

}
