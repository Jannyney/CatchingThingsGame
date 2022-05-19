package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame implements ActionListener, ItemListener {

	Game() {
		super("Catching Things Game");
		this.getContentPane().add(new GameScene());

		//this.getContentPane().add(new Menu(GameScene.scores, this, this));
		this.setSize(400, 700);
		this.setLocation(300,0);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void removeElement() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}

	public void GameOver(int scores) {
		removeElement();
		this.getContentPane().add(new Menu(GameScene.scores, this, this));
		setVisible(true);
		GameScene.scores = 0;
		GameScene.skyColor = new Color(49, 210, 252);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Play Again")) {
			removeElement();
			GameScene gameScene = new GameScene();
			this.getContentPane().add(gameScene);
			gameScene.requestFocus();
			//setVisible(true);
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (Menu.scenes.getSelectedItem() == "Day" && e.getStateChange() == 1) {
			GameScene.skyColor = new Color(255,242,215);
			GameScene.sunColor = Color.orange;
			GameScene.fontColor = Color.BLACK;
		} else if (Menu.scenes.getSelectedItem() == "Night" && e.getStateChange() == 1) {
			GameScene.skyColor = new Color(0, 102, 171);
			GameScene.sunColor = new Color(254, 234, 147);
			GameScene.fontColor = Color.WHITE;
		}

	}
	
}
