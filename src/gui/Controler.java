package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pixelazer.Transformer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public final class Controler {
	private final ImageView imageView = new ImageView(DEFAULT_IMG);
	private final static String DEFAULT_IMG = "http://mikecann.co.uk/wp-content/uploads/2009/12/javafx_logo_color_1.jpg";

	public Controler() {}

	public ImageView getImageView() {
		return imageView;
	}

	public EventHandler<ActionEvent> getHandler(Events eventType) {
		switch (eventType) {
		case loadFile:
			return loadFile;
		case saveFile:
			return saveFile;
		case applyGrayscale:
			return applyGrayscale;
		case applyBrighter:
			return applyBrighter;
		case applyDarker:
			return applyDarker;
		case drawMandelBrot:
			return drawMandelbrotSet;
		}
		throw new NoSuchElementException("Such eventType " + eventType + " not found");
	}

	private EventHandler<ActionEvent> loadFile = event -> {
		FileChooser fileChooser = new FileChooser();

		//Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		//Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		Image img_in = null;
		try {
			img_in = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageView.setImage(img_in);
	};

	private EventHandler<ActionEvent> saveFile = new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent event) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Image");

			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	};

	private EventHandler<ActionEvent> applyGrayscale = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.grayscale(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> applyBrighter = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.brighter(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> applyDarker = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.darker(image_in);
		imageView.setImage(image_out);
	};

	private EventHandler<ActionEvent> drawMandelbrotSet = event -> {
		Image image_in = imageView.getImage();
		Image image_out = Transformer.drawMandelbrot(image_in);
		imageView.setImage(image_out);
	};

}
