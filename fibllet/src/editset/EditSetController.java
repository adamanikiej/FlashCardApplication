package editset;

import flashcard.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EditSetController {

	
	@FXML
	AnchorPane application;

	@FXML
	private Button exitBtn;
	
	@FXML
	private TextField name; 
	
	
	public void initData(Set set) {
		name.setText(set.getSetName());
	}
	
	
	@FXML
	private void exitBtnClick() {
		//TODO add another modal for if they want to save changes or not
		Stage stg = (Stage) exitBtn.getScene().getWindow();
		stg.close();
	}

	@FXML
	private void minimizeBtnClick() {

	}

	@FXML
	private void fullscreenBtnClick() {

	}

	@FXML
	private void saveBtnClick(MouseEvent event) throws Exception {
		System.out.println("clicked save btn");
		//TODO add saving changes
		Node source = (Node) event.getSource();
		Stage stg = (Stage) source.getScene().getWindow();
		stg.close();
		
		/*
		Parent root = FXMLLoader.load((getClass().getResource("/mainmenu/menu.fxml")));
		Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add(getClass().getResource("/mainmenu/mainMenuStylesheet.css").toExternalForm());
		root.setStyle("-fx-background-color:transparent;");

		Stage window = (Stage) application.getScene().getWindow();
		window.setScene(scene);
		*/
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
