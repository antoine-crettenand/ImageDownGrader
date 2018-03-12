package pixelazer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Transformer {


	public static Image toGreyscale(Image image_in){
		PixelReader preader = image_in.getPixelReader();
		WritableImage wimage = new WritableImage((int) image_in.getWidth(), (int) image_in.getHeight());
		PixelWriter pwriter = wimage.getPixelWriter();
		for (int i = 0; i < (int) image_in.getHeight(); i++) {
			for (int j = 0; j < (int) image_in.getWidth(); j++) {
				Color col = preader.getColor(j, i);
				//Reading each pixel and converting it into gray scale
				pwriter.setColor(j, i, new Color((col.getRed() * 0.3 + col.getGreen() * 0.59 + col.getBlue() * 0.11), (col.getRed() * 0.3 + col.getGreen() * 0.59 + col.getBlue() * 0.11), (col.getRed() * 0.3 + col.getGreen() * 0.59 + col.getBlue() * 0.11), 1.0));
			}
		}
		return wimage;
	}
}
