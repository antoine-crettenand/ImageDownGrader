import gui.Controler;
import gui.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class Pixelazer extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override public void start(Stage primaryStage) {

		Pane root = new View(new Controler()).getRoot();

		Scene scene = new Scene(root);
		primaryStage.setTitle("Pixelazer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
