package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class LevelState extends GameState {
	
	
	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(0,128,0));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
