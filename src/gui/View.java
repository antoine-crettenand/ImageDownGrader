package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static gui.Event.*;
import static gui.IOEvent.*;

/**
 * Class represents the visual interface for the user
 */
public final class View {
	private final MenuBar menuBar;
	private final ScrollPane image;
	private final Controler controler;

	public Pane getRoot() {
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(image);
		return root;
	}

	public View(Controler controler) {
		this.controler = controler;
		menuBar = setUpMenuBar();
		image = setUpImage();
	}

	private MenuBar setUpMenuBar() {

		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		MenuItem menuFile_newFile = new MenuItem("New");
		menuFile_newFile.setOnAction(controler.handle(loadFile));
		MenuItem menuFile_saveFile = new MenuItem("Save As");
		menuFile_saveFile.setOnAction(controler.handle(saveFile));
		menuFile.getItems().addAll(menuFile_newFile, menuFile_saveFile);

		Menu menuEdit = new Menu("Edit");
		MenuItem menuEdit_applyGreyscale = new MenuItem("Greyscale");
		menuEdit_applyGreyscale.setOnAction(controler.handle(applyGrayscale));
		MenuItem menuEdit_applyBrighter = new MenuItem("Brighter");
		menuEdit_applyBrighter.setOnAction(controler.handle(applyBrighter));
		MenuItem menuEdit_applyDarker = new MenuItem("Darker");
		menuEdit_applyDarker.setOnAction(controler.handle(applyDarker));
		MenuItem menuEdit_applyInvert = new MenuItem("Invert");
		menuEdit_applyInvert.setOnAction(controler.handle(applyInvert));

		menuEdit.getItems().addAll(menuEdit_applyGreyscale, menuEdit_applyBrighter, menuEdit_applyDarker, menuEdit_applyInvert);

		Menu menuView = new Menu("View");

		MenuItem menuView_bigger = new MenuItem("Bigger");
		menuView_bigger.setOnAction(controler.handle(bigger));
		MenuItem menuView_smaller = new MenuItem("Smaller");
		menuView_smaller.setOnAction(controler.handle(smaller));

		MenuItem menuView_drawMandelbrotSet = new MenuItem("MandelBrot Set");
		menuView_drawMandelbrotSet.setOnAction(controler.handle(drawMandelBrot));
		menuView.getItems().addAll(menuView_drawMandelbrotSet, menuView_bigger, menuView_smaller);

		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		return menuBar;
	}

	private ScrollPane setUpImage() {
		return new ScrollPane(controler.getImageView());
	}
}

