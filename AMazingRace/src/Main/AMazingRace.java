package Main;

import javax.swing.JFrame;

public class AMazingRace {

	public static void main(String[] args) {

		JFrame window = new JFrame("A Mazing Race");
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		//window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

	}

}
