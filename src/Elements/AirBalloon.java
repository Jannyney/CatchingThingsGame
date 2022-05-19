package Elements;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class AirBalloon extends Object{
	public AirBalloon(int x, int y) {
		super(x, y);
	}

	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image\\balloon.png"));
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
