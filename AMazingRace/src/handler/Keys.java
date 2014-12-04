package handler;

import java.awt.event.KeyEvent;

/*
 * Class: Keys.java
 * Author: Raid Alawar
 * Purpose: Manages the key input from the player.
 * 
 */

public class Keys {

	public static final int NUM_KEYS = 6;
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int ENTER = 4;
	public static final int ESCAPE = 5;

	public static boolean[] keyState = new boolean[NUM_KEYS];

	public static void keySet(int i, boolean b) {

		if (i == KeyEvent.VK_UP) {
			keyState[UP] = b;
		} else if (i == KeyEvent.VK_LEFT) {
			keyState[LEFT] = b;

		} else if (i == KeyEvent.VK_DOWN) {
			keyState[DOWN] = b;

		} else if (i == KeyEvent.VK_RIGHT) {
			keyState[RIGHT] = b;

		} else if (i == KeyEvent.VK_ENTER) {
			keyState[ENTER] = b;

		} else if (i == KeyEvent.VK_ESCAPE) {
			keyState[ESCAPE] = b;
		}

	}

	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			keyState[i] = false;
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i];

	}

}
