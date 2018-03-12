package pixelazer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.Function;

public class Transformer {

	public static Image paint(Image image_in, Function<Color, Color> painter){
		PixelReader preader = image_in.getPixelReader();
		WritableImage wimage = new WritableImage((int) image_in.getWidth(), (int) image_in.getHeight());
		PixelWriter pwriter = wimage.getPixelWriter();
		for (int i = 0; i < wimage.getHeight(); i++) {
			for (int j = 0; j < wimage.getWidth(); j++) {
				Color col = preader.getColor(j, i);
				pwriter.setColor(j, i, painter.apply(col));
			}
		}

		return wimage;
	}

	public static Image grayscale(Image image_in){
		return paint(image_in, Color::grayscale);
	}

	public static Image brighter(Image image_in){
		return paint(image_in, Color::brighter);
	}

	public static Image darker(Image image_in){
		return paint(image_in, Color::darker);
	}


}
