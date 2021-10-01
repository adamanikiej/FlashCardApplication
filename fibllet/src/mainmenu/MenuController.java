package mainmenu;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MenuController {
	
	@FXML
	AnchorPane application;

	@FXML
	private AnchorPane reviewSetsPane;

	@FXML
	private Button exitBtn;

	@FXML
	private Label term1;

	@FXML
	private void exitBtnClick() {
		Stage stg = (Stage) exitBtn.getScene().getWindow();
		stg.close();
	}

	@FXML
	private void minimizeBtnClick() {

	}

	@FXML
	private void fullscreenBtnClick() {

	}

	
	//FRONT MENU BUTTON ACTIONS
	@FXML
	public void reviewBtnClick() throws Exception {
		System.out.println("clicked review btn");

		Parent root = FXMLLoader.load((getClass().getResource("/reviewmenu/reviewSets.fxml")));
		root.setStyle("-fx-background-color:transparent;");
		Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add(getClass().getResource("/reviewmenu/reviewSetsStylesheet.css").toExternalForm());
		
		Stage window = (Stage) application.getScene().getWindow();
		window.setScene(scene);
		
	}
	
	@FXML
	private void quizBtnClick() {
		System.out.println("clicked quiz btn");
	}
	
	@FXML
	private void createSetBtnClick() throws Exception {
		System.out.println("clicked create set btn");
		
		Parent root = FXMLLoader.load((getClass().getResource("/newsetmenu/createEditSets.fxml")));
		root.setStyle("-fx-background-color:transparent;");
		Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add(getClass().getResource("/newsetmenu/createEditSetsStylesheet.css").toExternalForm());
		
		Stage window = (Stage) application.getScene().getWindow();
		window.setScene(scene);
	}
	
	@FXML
	private void shameBtnClick() {
		System.out.println("clicked shame btn");
	}
	
	@FXML
	private void progressBtnClick() {
		System.out.println("clicked progress btn");
	}

	// LOGIC FOR BEING ABLE TO DRAG THE APPLICATION
	private double startMoveX = 0, startMoveY = 0;
	private Boolean dragging = false;

	@FXML
	public void toolBarDrag(MouseEvent evt) {
		
		startMoveX = evt.getSceneX();
		startMoveY = evt.getSceneY();
		dragging = true;
	}

	public void resetMoveOperation() {
		startMoveX = 0;
		startMoveY = 0;
		dragging = false;
	}

	@FXML
	private void toolBarDragged(MouseEvent evt) {
		if (dragging) {
			Window w = application.getScene().getWindow();
			w.setX(evt.getScreenX() - startMoveX);
			w.setY(evt.getScreenY() - startMoveY);
		}
	}

	@FXML
	private void toolBarReleased(MouseEvent evt) {
		resetMoveOperation();
	}

}
