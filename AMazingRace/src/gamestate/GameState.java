package gamestate;

import java.awt.Graphics2D;

/*
 * Class: GameState.java
 * Author: Raid Alawar
 * Purpose: This abstract class sets up
 * the blueprints for the game states.
 * 
 */

public abstract class GameState {

	protected GameStateManager gsm;

	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	protected abstract void init();

	protected abstract void update();

	protected abstract void draw(Graphics2D g);

	protected abstract void handleInput();

}
