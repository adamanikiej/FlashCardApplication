package mainmenu;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		//inistalize the menu scene
		initializeMenu(stage);

		
		// normal();
//		StackPane node = new StackPane(new Label("TRANSPARENT"));
//		node.setPadding(new Insets(15));
//		node.setStyle("-fx-background-color:green;-fx-font-size:16px;");

//		button = new Button("test");
//		//button.setStyle("-fx-background-color: transparent;");
//		StackPane layout = new StackPane();
//		
//		
//		layout.getChildren().add(button);
//		primaryStage.initStyle(StageStyle.UNDECORATED);
//		Scene scene = new Scene(layout, 1000, 600);
//		//primaryStage.initStyle(StageStyle.TRANSPARENT);
//		//scene.setFill(Color.TRANSPARENT);
//		primaryStage.setOpacity(0.4);
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	/**
	 * Method for intializing the main menu for fuizzlet
	 * @param stage 
	 * @throws Exception
	 */
	public void initializeMenu(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add(getClass().getResource("mainMenuStylesheet.css").toExternalForm());

		//scene.setFill(Color.TRANSPARENT);
		root.setStyle("-fx-background-color:transparent;");
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	//useless code -> first attempt at transparent background below
	public void normal() {
		StackPane body = new StackPane();
		VBox.setVgrow(body, Priority.ALWAYS);
		VBox root = new VBox(buildDraggableHeader(), body);
		root.setStyle("-fx-border-width:0px;-fx-border-color:green;-fx-background-color:transparent;");

		Stage stg = new Stage();
		stg.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(root, 1000, 600, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Gafata");
		stg.setScene(scene);

		// stg.setScene(new Scene(root, 1000, 600));
		// EXPLICITLY INCLUDING THE RESIZE LOGIC !!!
		// stg.getScene().addEventFilter(MouseEvent.ANY, new ResizeHandler(stg));
		stg.show();
	}

	private Node buildDraggableHeader() {
		// exit button
		Button exitBtn = new Button("X");
		exitBtn.setOnAction(e -> {
			Stage stg = (Stage) exitBtn.getScene().getWindow();
			stg.close();
		});
		BorderPane header = new BorderPane();
		header.setMinHeight(30);
		header.setLeft(new HBox(exitBtn, new Button("0"), new Button("-")));
		Label label = new Label("Fuizzlet");

		label.setStyle("-fx-font-family: Gafata; -fx-font-size: 20;");
		label.setTextFill(Color.WHITE);
		// label.setFont(new Font());
		StackPane title = new StackPane(label);
		title.setStyle("-fx-font-weight:bold;");
		title.setAlignment(Pos.CENTER);
		header.setCenter(title);
		header.setStyle("-fx-background-color:#99ccfe;-fx-border-width: 0 0 2 0;-fx-border-color:#99ccfe;");
		header.setPadding(new Insets(0, 0, 0, 3));

		// EXPLICITLY INCLUDING THE DRAG LOGIC !!!
		DoubleProperty x = new SimpleDoubleProperty();
		DoubleProperty y = new SimpleDoubleProperty();
		header.setOnMousePressed(e -> {
			x.set(e.getSceneX());
			y.set(e.getSceneY());
		});
		header.setOnMouseDragged(e -> {
			header.getScene().getWindow().setX(e.getScreenX() - x.get());
			header.getScene().getWindow().setY(e.getScreenY() - y.get());
		});

		return header;
	}

}