package GameState;

import java.util.ArrayList;

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
	
	public void loadState(int state){
		if(state == MENUSTATE){
			gameStates[MENUSTATE] = new MenuState(this);
		}
	}
	
	public void unloadState(int state){
		gameStates[state] = null;
	}

	

}
