package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import maze.Maze;
import misc.Player;
import misc.Timer;

public class LevelState extends GameState {

	private Player player;
	private Maze maze;
	private Timer timer;

	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {

		maze = new Maze(0, 0, 25, 25);
		player = new Player(1, GamePanel.HEIGHT / 2);
		timer = new Timer(10000);

	}

	public void update() {
		handleInput();
		player.update();
		timer.update();
	}

	private void handleInput() {

	}

	public void draw(Graphics2D g) {

		// draw background
		g.setColor(new Color(0, 128, 0));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		player.draw(g);
		maze.draw(g);
		//timer.draw(g);

	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {
	}

}
