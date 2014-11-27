package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class GameStateManager {

	public static final int NUMGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;

	private GameState[] gameStates;
	private int currentState;

	public GameStateManager() {

		gameStates = new GameState[NUMGAMESTATES];

		currentState = MENUSTATE;
		loadState(currentState);

	}

	public void loadState(int state) {
		if (state == MENUSTATE) {
			gameStates[MENUSTATE] = new MenuState(this);
		} else if (state == LEVELSTATE) {
			gameStates[LEVELSTATE] = new LevelState(this);
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

	public void update() {
		if(gameStates[currentState] != null){
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g){
		if(gameStates[currentState] != null){
			gameStates[currentState].draw(g);
		}
		else{
			g.setColor(Color.BLACK);
			g.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

}
