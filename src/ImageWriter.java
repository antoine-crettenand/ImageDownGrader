import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class ImageWriter {

	public static BufferedImage writeImage(BufferedImage image, Function<Integer, Integer> coloring){
		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage newImage = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				newImage.setRGB(i, j, coloring.apply(newImage.getRGB(i, j)));
			}
		}

		return newImage;
	}

	public static void writeFile(BufferedImage image, String name){
		try {
			// retrieve image
			File outputfile = new File(name);
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Cannot write File" + image);
		}
	}
}
