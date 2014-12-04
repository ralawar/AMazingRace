package gamestate;

import handler.Keys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import maze.Maze;
import misc.Player;
import misc.Timer;

/*
 * Class: LevelState.java
 * Author: Raid Alawar
 * Purpose: This is the game state where the player
 * plays the game. It manages the player, timer, maze,
 * and game results.
 * 
 */

public class LevelState extends GameState {

	private Player player;
	private Maze maze;
	private Timer timer;
	private int mazeHeight;
	private int mazeWidth;

	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {

		mazeHeight = 25;
		mazeWidth = 25;

		maze = new Maze(mazeWidth, mazeHeight);
		player = new Player(maze.getStartCell().getX() * GamePanel.WIDTH / mazeWidth, maze
				.getStartCell().getY() * GamePanel.HEIGHT / mazeHeight + 7);
		timer = new Timer(75, gsm);

		gsm.setPlayerWon(false);
	}

	public void update() {
		handleInput();
		timer.update();
	}

	protected void handleInput() {

		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}

		int x = player.getX();
		int y = player.getY();
		int speed = player.getSpeed();

		// prevents the player from going outside boundaries or through objects
		// also checks if the player reached the end zone and won
		if (Keys.isPressed(Keys.UP) && !playerCollision(x, y - speed) && y - speed >= 0) {
			player.setY(y - speed);
			if (playerWins(player.getX(), player.getY())) {
				gsm.setPlayerWon(true);
				gsm.setState(GameStateManager.GAMEOVERSTATE);
			}
		}
		if (Keys.isPressed(Keys.DOWN) && !playerCollision(x, y + speed)
				&& y + speed < GamePanel.HEIGHT - 10) {
			player.setY(y + speed);
			if (playerWins(player.getX(), player.getY())) {
				gsm.setPlayerWon(true);
				gsm.setState(GameStateManager.GAMEOVERSTATE);
			}
		}
		if (Keys.isPressed(Keys.LEFT) && !playerCollision(x - speed, y) && x - speed >= 0) {
			player.setX(x - speed);
			if (playerWins(player.getX(), player.getY())) {
				gsm.setPlayerWon(true);
				gsm.setState(GameStateManager.GAMEOVERSTATE);
			}
		}
		if (Keys.isPressed(Keys.RIGHT) && !playerCollision(x + speed, y)
				&& x + speed < GamePanel.WIDTH - 10) {
			player.setX(x + speed);
			if (playerWins(player.getX(), player.getY())) {
				gsm.setPlayerWon(true);
				gsm.setState(GameStateManager.GAMEOVERSTATE);
			}
		}

	}

	protected boolean playerCollision(int x, int y) {

		for (Rectangle wall : maze.getWallGraphics()) {
			if (wall.intersects(x, y, player.getSize(), player.getSize())) {
				return true;
			}
		}

		return false;
	}

	public boolean playerWins(int x, int y) {
		if (new Rectangle((int) (maze.getEndCell().getX() * maze.getWidthScale()), (int) (maze
				.getEndCell().getY() * maze.getHeightScale()), (int) maze.getWidthScale(),
				(int) maze.getHeightScale()).intersects(x, y, player.getSize(), player.getSize())) {
			return true;

		}

		return false;
	}

	public void draw(Graphics2D g) {

		// draw background
		g.setColor(new Color(0, 128, 0));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		maze.draw(g);
		player.draw(g);
		timer.draw(g);

	}

}
