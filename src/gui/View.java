package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static gui.Events.*;

public class View {
	private MenuBar top;
	private ScrollPane center;
	private Controler controler;

	public Pane getRoot() {
		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setCenter(center);
		return root;
	}

	public View(Controler controler) {
		this.controler = controler;
		top = setUpMenuBar();
		center = setUpImage();
	}

	private MenuBar setUpMenuBar() {

		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		MenuItem menuFile_newFile = new MenuItem("New");
		menuFile_newFile.setOnAction(controler.getHandler(loadFile));
		MenuItem menuFile_saveFile = new MenuItem("Save As");
		menuFile_saveFile.setOnAction(controler.getHandler(saveFile));
		menuFile.getItems().addAll(menuFile_newFile, menuFile_saveFile);

		Menu menuEdit = new Menu("Edit");
		MenuItem menuEdit_applyGreyscale = new MenuItem("Greyscale");
		menuEdit_applyGreyscale.setOnAction(controler.getHandler(applyBinary));
		menuEdit.getItems().add(menuEdit_applyGreyscale);

		Menu menuView = new Menu("View");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		return menuBar;
	}

	private ScrollPane setUpImage() {
		return new ScrollPane(controler.getImageView());
	}
}

