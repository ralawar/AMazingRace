package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;

/*
 * Class: GameStateManager.java
 * Author: Raid Alawar
 * Purpose: Adds, deletes, switches, updates,
 * and draws game states as needed.
 * 
 */

public class GameStateManager {

	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int GAMEOVERSTATE = 2;

	private GameState[] gameStates;
	private int currentState;
	private PauseState pauseState;
	private boolean paused;
	private boolean playerWon;

	public GameStateManager() {

		gameStates = new GameState[NUMGAMESTATES];

		pauseState = new PauseState(this);
		paused = false;

		playerWon = false;

		currentState = MENUSTATE;
		loadState(currentState);

	}

	public void loadState(int state) {
		if (state == MENUSTATE) {
			gameStates[MENUSTATE] = new MenuState(this);
		} else if (state == LEVELSTATE) {
			gameStates[LEVELSTATE] = new LevelState(this);
		} else if (state == GAMEOVERSTATE) {
			gameStates[GAMEOVERSTATE] = new GameOverState(this);
		}
	}

	public void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public boolean isPaused() {
		return paused;
	}

	public int getCurrentState() {
		return currentState;
	}

	public boolean playerWon() {
		return playerWon;
	}

	public void setPlayerWon(boolean b) {
		playerWon = b;
	}

	public void update() {

		if (paused) {
			pauseState.update();
			return;
		}

		if (gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}

	public void draw(Graphics2D g) {

		if (paused) {
			pauseState.draw(g);
		} else if (gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}
}
