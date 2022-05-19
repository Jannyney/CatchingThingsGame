package Game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import static java.lang.Math.random;

import Elements.*;
import Elements.Object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

public class GameScene extends JPanel implements ActionListener, KeyListener {
	static int scores;
	static Game game;
	int speed = 20;
	static Color skyColor = new Color(49, 210, 252);
	static Color sunColor = Color.ORANGE;
	static Color fontColor = Color.black;
	private Timer timer = new Timer(speed, this);
	int lvl = 1;
	static int startPoint = -60;
	int x = 150;
	int y = 570;
	int bkWidth = 90;
	int bkHeight = 80;

	int xCloud = 100;
	int yCloud = 150;
	int xPlane = 480;
	int yPlane = 50;
	int xAirB = -100;
	int yAirB = 500;

	int x1 = (int) (10 + random() * 310);;
	int y1 = 0;
	int x2 = (int) (10 + random() * 310);;
	int y2 = startPoint;
	int x3 = (int) (10 + random() * 310);;
	int y3 = -220;
	int x4 = (int) (10 + random() * 310);;
	int y4 = -500;

	static int hamCount = 0;
	static int colaCount = 0;
	static int pizzaCount = 0;

	Basket basket = new Basket(x, y);
	Hamburger hamburger = new Hamburger(x1, y1);
	Cola cola = new Cola(x2, y2);  
	Pizza pizza = new Pizza(x3, y3);
	Bomb bomb = new Bomb(x4, y4);
	Garden garden = new Garden(-100, 400);
	Object cloud = new Cloud(xCloud, yCloud); // Implicit Casting
	Plane plane = new Plane(xPlane, yPlane);
	Arrow arrow = new Arrow(130,320);
	AirBalloon airBalloon = new AirBalloon(xAirB,yAirB);

	GameScene() {
		this.setBackground(skyColor);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g1 = (Graphics2D) g;
		g.setColor(sunColor);
		g.fillOval(300, 100, 50, 50);
		g1.drawImage(garden.getImage(), garden.x, garden.y, 900, 300, null);
		g1.drawImage(((Cloud) cloud).getImage(), cloud.x, cloud.y, 100, 50, null);  // Explicit Casting
		g1.drawImage(plane.getImage(), plane.x, plane.y, 80, 30, null);
		g1.drawImage(airBalloon.getImage(), airBalloon.x, airBalloon.y, 70, 80, null);
		g1.drawImage(basket.getImage(), basket.x, basket.y, bkWidth, bkHeight, null);

		checkCatch(basket, hamburger, cola, pizza, bomb, bkWidth);
		g1.drawImage(hamburger.getImage(), hamburger.x, hamburger.y, 80, 80, null);
		g1.drawImage(cola.getImage(), cola.x, cola.y, 25, 55, null);
		g1.drawImage(pizza.getImage(), pizza.x, pizza.y, 70, 70, null);
		g1.drawImage(bomb.getImage(), bomb.x, bomb.y, 60, 60, null);
		g.setColor(Color.WHITE);

		g.setFont(MyFont.getFont(30));
		g.drawString("Score: " + scores, 50, 30);
		g.drawString("Level: " + lvl, 250, 30);

		g.setColor(Color.WHITE);
		g.setFont(MyFont.getFont(60));
		if (scores == 0) {
			g.drawString("Level 1", 100, 300);
			g.setFont(MyFont.getFont(20));
			g.setColor(fontColor);
			g1.drawImage(arrow.getImage(), arrow.x, arrow.y, 130, 60, null);
			g.drawString("Left", 85, 360);
			g.drawString("Right", 260, 360);
			g.drawString("Don't hit the Bomb!", 100, 400);
			
		} else if (scores >= 100 && scores < 200) {
			if (scores <= 130) {
				g.drawString("Level 2", 100, 300);
			}
			skyColor = new Color(0, 156, 233);
			this.setBackground(skyColor);

		} else if (scores >= 200) {
			if (scores <= 240) {
				g.drawString("Level 3", 100, 300);
			}
			skyColor = new Color(0, 102, 171);
			sunColor = new Color(254, 234, 147);
			this.setBackground(skyColor);
		}
		repaint();
	}

	public static void checkCatch(Basket basket, Hamburger hamburger, Cola cola, Pizza pizza, Bomb bomb, int bkWidth) {

		if (basket.y <= hamburger.y + 40) {
			if (basket.x > hamburger.x + 40 || basket.x + bkWidth < hamburger.x) {
				if (hamburger.y + 40 >= basket.y + 70)
					game.GameOver(scores);
			} else {
				scores += 10;
				hamCount += 1;
				hamburger.x = (int) (10 + random() * 310);
				hamburger.y = startPoint;
			}
		}
		if (basket.y < cola.y + 45) {
			if (basket.x > cola.x + 40 || basket.x + bkWidth < cola.x) {
				if (cola.y + 45 >= basket.y + 70)
					game.GameOver(scores);
			} else {
				scores += 20;
				colaCount += 1;
				cola.x = (int) (10 + random() * 310);
				cola.y = startPoint;
			}
		}
		if (basket.y < pizza.y + 40) {
			if (basket.x > pizza.x + 40 || basket.x + bkWidth < pizza.x) {
				if (pizza.y + 40 >= basket.y + 70)
					game.GameOver(scores);
			} else {
				scores += 30;
				pizzaCount += 1;
				pizza.x = (int) (10 + random() * 310);
				pizza.y = startPoint;
			}
		}

		if (basket.y < bomb.y) {
			if (basket.x <= bomb.x + 55 && basket.x + bkWidth >= bomb.x) {
				game.GameOver(scores);
			} else {
				bomb.y += 5;
				bomb.x = (int) (10 + Math.random() * 310);
				bomb.y = -200;

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (scores >= 0 && scores < 100) {
			hamburger.y += 6;
			bomb.y += 6;
			if (hamburger.y > 180 || pizza.y > 180 || cola.y > 180) {
				cola.y += 6;
				pizza.y += 6;
			}
		} else if (scores >= 100 && scores < 200) {
			lvl = 2;
			hamburger.y += 8;
			bomb.y += 8;
			if (hamburger.y > 180 || pizza.y > 180 || cola.y > 180) {
				cola.y += 8;
				pizza.y += 8;
			}
		} else {
			lvl = 3;
			hamburger.y += 10;
			bomb.y += (int) (10 + Math.random() * 5);

			if (hamburger.y > 180 || pizza.y > 180 || cola.y > 180) {
				cola.y += 10;
				pizza.y += 10;
			}
		}

		if (basket.x >= 290) {
			basket.x = 290;
		}
		if (basket.x <= 0) {
			basket.x = 0;
		}
		cloud.x += 1;
		if (cloud.x >= 400) {
			cloud.x = -100;
		}

		plane.x -= 1;
		if (plane.x <= -80) {
			plane.x = 400;
		}
		
		airBalloon.x += 1;
		airBalloon.y -= 1;
		if (airBalloon.x >= 400 && airBalloon.y <= 0) {
			airBalloon.x = -100;
			airBalloon.y = 400;
		}

		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			basket.x -= 25;
			break;
		case KeyEvent.VK_RIGHT:
			basket.x += 25;
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		game = new Game();

	}
}
