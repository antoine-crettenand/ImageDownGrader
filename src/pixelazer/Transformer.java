package pixelazer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Transformer {

	/**
	 * Repaint depends on current Color
	 *
	 * @param image_in
	 * @param painter
	 * @return
	 */
	public static Image rePaint(Image image_in, Function<Color, Color> painter) {
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

	/**
	 * Paint doesn't depends on current color, draw as on white canvas
	 *
	 * @param image_in
	 * @param painter
	 * @return
	 */
	private static Image paint(Image image_in, BiFunction<Integer, Integer, Color> painter) {
		WritableImage wimage = new WritableImage((int) image_in.getWidth(), (int) image_in.getHeight());
		PixelWriter pwriter = wimage.getPixelWriter();
		for (int i = 0; i < wimage.getHeight(); i++) {
			for (int j = 0; j < wimage.getWidth(); j++) {
				pwriter.setColor(j, i, painter.apply(j, i));
			}
		}

		return wimage;
	}

	public static Image grayscale(Image image_in) {
		return rePaint(image_in, Color::grayscale);
	}

	public static Image brighter(Image image_in) {
		return rePaint(image_in, Color::brighter);
	}

	public static Image darker(Image image_in) {
		return rePaint(image_in, Color::darker);
	}

	public static Image invert(Image image_in) {
		return rePaint(image_in, Color::invert);
	}

	public static Image drawMandelbrot(Image image_in) {
		return paint(image_in,
				MandelbrotSet.mandelbrotPainter(500, ((int) image_in.getWidth()), ((int) image_in.getHeight())));
	}

	public static Image drawCircle(Image image_in, int x, int y) {
		PixelReader pixelReader = image_in.getPixelReader();
		WritableImage wi = new WritableImage(pixelReader, (int) image_in.getWidth(), (int) image_in.getHeight());
		PixelWriter pixelWriter = wi.getPixelWriter();

		pixelWriter.setPixels(x, y, 50, 50, pixelReader, x, y);

		return wi;
	}

}
