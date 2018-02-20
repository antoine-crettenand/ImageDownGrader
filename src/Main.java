import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		BufferedImage getImage = ImageReader.readImage("mario.png");
		Function<Integer, Integer> downgrade = (x) -> (x == 16777215 ? Color.RED.getRGB() : x);
		System.out.println(getImage.getRGB(0,0));
		System.out.println(getImage.getRGB(2,0));
		BufferedImage out = ImageWriter.writeImage(getImage, downgrade);
		ImageWriter.writeFile(out, "marioDowngraded.png");
	}
}
