package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RootScene {
	private MenuBar top;
	private ScrollPane center;
	private ImageRenderer imageRenderer;

	public BorderPane getRoot() {
		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setCenter(center);
		return root;
	}

	public RootScene() {
		imageRenderer = new ImageRenderer();
		top = setUpMenuBar();
		center = setUpImage();
	}

	private MenuBar setUpMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem newFile = new MenuItem("New");
		newFile.setOnAction(loadFile);
		MenuItem saveFile = new MenuItem("Save As");
		saveFile.setOnAction(this.saveFile);
		menuFile.getItems().addAll(newFile, saveFile);
		Menu menuEdit = new Menu("Edit");
		Menu menuView = new Menu("View");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		return menuBar;
	}

	private ScrollPane setUpImage() {
		return new ScrollPane(imageRenderer.getDisplayedImg());
	}

	private javafx.event.EventHandler<ActionEvent> loadFile = event -> {
		FileChooser fileChooser = new FileChooser();

		//Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		//Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageRenderer.setDisplayedImg(image);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	};

	private javafx.event.EventHandler<ActionEvent> saveFile = new EventHandler<ActionEvent>() {

		@Override public void handle(ActionEvent event) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Image");

			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(imageRenderer.getDisplayedImg().getImage(), null), "png",
							file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	};
}

