package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

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
		EventController eventController = new EventController(imageRenderer);

		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		MenuItem newFile = new MenuItem("New");
		newFile.setOnAction(eventController.getHandler(Events.loadFile));
		MenuItem saveFile = new MenuItem("Save As");
		saveFile.setOnAction(eventController.getHandler(Events.saveFile));
		menuFile.getItems().addAll(newFile, saveFile);

		Menu menuEdit = new Menu("Edit");
		MenuItem applyBinary = new MenuItem("Grayscale");
		applyBinary.setOnAction(eventController.getHandler(Events.applyBinary));
		menuEdit.getItems().add(applyBinary);

		Menu menuView = new Menu("View");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		return menuBar;
	}

	private ScrollPane setUpImage() {
		return new ScrollPane(imageRenderer.getDisplayedImg());
	}
}

