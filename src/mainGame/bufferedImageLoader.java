package mainGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String Path) {
		
		try {
			image = ImageIO.read(getClass().getResource(Path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
