package Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import Elements.Basket;
import Elements.Cola;
import Elements.Hamburger;
import Elements.MyFont;
import Elements.Pizza;
import Elements.ThreeStars;

public class Menu extends JPanel {

	public int scores;
	JLabel gameOver = new JLabel(" Game Over!");
	JLabel showScores = new JLabel("     Your Score    ");
	JLabel myScore = new JLabel("     " + GameScene.scores + "");
	static String scene[] = { "Day", "Night" };
	static JComboBox scenes = new JComboBox(scene);
	JButton playAgain = new JButton("Play Again");
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	
	Color c = new Color(0, 196, 250);
	//Color c = new Color(28,238,91);
	ThreeStars tStars = new ThreeStars(125, 355);
	Hamburger hamburger = new Hamburger(30, 410);
	Cola cola = new Cola(60, 480);
	Pizza pizza = new Pizza(40, 530);

	Menu() {
	}

	Menu(int scores, ActionListener main, ItemListener main2) {

		
		this.setBackground(c);

		playAgain.setForeground(Color.BLACK);
		gameOver.setFont(MyFont.getFont(54));
		showScores.setFont(MyFont.getFont(40));
		scenes.setFont(MyFont.getFont(25));
		playAgain.setFont(MyFont.getFont(30));
		
		myScore.setFont(new Font("Ariel", Font.BOLD, 80));
		scenes.setBackground(new Color(181, 234, 250));

		playAgain.setBackground(Color.ORANGE);

		this.scores = GameScene.scores;
		this.setFocusable(true);

		p1.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(2, 1));
	
		p2.add(gameOver);
		p2.add(showScores);
		
		p1.add(p2, BorderLayout.NORTH);
		p1.add(myScore, BorderLayout.CENTER);
		p1.add(scenes, BorderLayout.SOUTH);

		this.add(p1);

		this.add(playAgain);
		p1.setBackground(c);
		p2.setBackground(c);
		
		playAgain.addActionListener(main);
		scenes.addItemListener(main2);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(8,10,31));
		g.fillRoundRect(25, 380, 340, 225,25,25);
		g.setColor(new Color(0,137,212));
		g.fillRoundRect(30, 390, 330, 210,25,25);
		g.setColor(Color.BLACK);
		g.fillRect(0,630,400,70);
		Graphics2D g1 = (Graphics2D) g;
		g1.drawImage(tStars.getImage(), tStars.x, tStars.y, 130, 70, null);
		g1.drawImage(hamburger.getImage(), hamburger.x, hamburger.y, 80, 80, null);
		g1.drawImage(cola.getImage(), cola.x, cola.y, 25, 55, null);
		g1.drawImage(pizza.getImage(), pizza.x, pizza.y, 70, 70, null);
		g.setColor(Color.WHITE);
		g.setFont(MyFont.getFont(27));
		g.drawString(": " + GameScene.hamCount + " pc(s)" , 120, 460);
		g.drawString(": " + GameScene.colaCount + " pc(s)", 120, 520);
		g.drawString(": " + GameScene.pizzaCount + " pc(s)", 120, 580);
	}
}
