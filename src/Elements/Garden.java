package Elements;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Garden extends Object{
	
	public Garden(int x, int y) {
		super(x, y);
	}

	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image\\garden1_nobg.png"));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
