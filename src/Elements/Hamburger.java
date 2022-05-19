package Elements;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Hamburger extends Object{
	public int x;
	public int y;
	
	public Hamburger(int x,int y) {
		super(x,y);
	}
	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image\\hamburger.png"));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
