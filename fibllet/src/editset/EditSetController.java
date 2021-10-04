package editset;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import flashcard.Flashcard;
import flashcard.Set;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EditSetController implements Initializable {

	
	@FXML
	AnchorPane application;

	@FXML
	private Button exitBtn;
	
	@FXML
	private TextField name; 
	
	private Set clickedSet;
	
	
	// setting up set table
	@FXML
	private TableView<Flashcard> tableView;
	@FXML
	private TableColumn<Flashcard, TextField> question;
	@FXML
	private TableColumn<Flashcard, TextField> answer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// set up columns for new Set
		question.setCellValueFactory(new PropertyValueFactory<Flashcard, TextField>("question"));
		answer.setCellValueFactory(new PropertyValueFactory<Flashcard, TextField>("answer"));
	}
	
	/**
	 * Function ran after initData so flashcards are loaded into Tableview
	 */
	public void loadFlashcards() {
		// populate set with data for testing
		tableView.setItems(clickedSet.getFlashcards());

		// set up event handler for tableview rows
		EventHandler<MouseEvent> onClick = this::handleRowMouseClick;
		tableView.setRowFactory(param -> {
			TableRow<Flashcard> row = new TableRow<>();
			row.setOnMouseClicked(onClick);
			return row;

		});
	}
	
	/**
	 * Handles mouse clicks on the rows of the tableview Dehighlights selected set
	 * if empty row is clicked on 
	 * TODO: add right click menu that allows to delete row
	 * 
	 * @param event
	 * @throws Exception 
	 */
	private void handleRowMouseClick(MouseEvent event) {
		@SuppressWarnings("unchecked")
		TableRow<Set> row = (TableRow<Set>) event.getSource();
		// System.out.println("mouse clicked:" + row.isEmpty());

		if (event.getButton() == MouseButton.PRIMARY) {
			if (row.isEmpty()) {
				// remove selection
				row.getTableView().getSelectionModel().clearSelection();
			} else if (event.getClickCount() == 2 && row.getItem() != null) {
				System.out.println("double clicked set");
				System.out.println(row.getItem());
				
				//go to edit set scene
				
				//consume the event
				event.consume();
			}
		}
	}
	
	
	
	public void initData(Set set) {
		name.setText(set.getSetName());
		this.clickedSet = set;
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
