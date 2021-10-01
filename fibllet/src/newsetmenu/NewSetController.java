package newsetmenu;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import flashcard.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewSetController implements Initializable {

	@FXML
	AnchorPane application;

	@FXML
	private Button exitBtn;

	@FXML
	Button menuBtn;
	
	// setting up set table
	@FXML
	private TableView<Set> tableView;
	@FXML
	private TableColumn<Set, String> setNameColumn;
	@FXML
	private TableColumn<Set, Integer> cardCountColumn;
	@FXML
	private TableColumn<Set, String> creationDateColumn;
	@FXML
	private TableColumn<Set, String> relatedQuizzesColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// set up columns for new Set
		setNameColumn.setCellValueFactory(new PropertyValueFactory<Set, String>("setName"));
		cardCountColumn.setCellValueFactory(new PropertyValueFactory<Set, Integer>("cardCount"));
		creationDateColumn.setCellValueFactory(new PropertyValueFactory<Set, String>("creationDate"));
		relatedQuizzesColumn.setCellValueFactory(new PropertyValueFactory<Set, String>("relatedQuizzes"));

		// populate set with data for testing
		tableView.setItems(getSets());

	}

	/**
	 * Function used to construct list of all items that should be put into
	 * tableview
	 * 
	 * @return
	 */
	public ObservableList<Set> getSets() {
		ObservableList<Set> sets = FXCollections.observableArrayList();

		ArrayList<String> related1 = new ArrayList<String>();
		related1.add("Anatomy");
		related1.add("Quiz 1");

		sets.add(new Set("Arm Anatomy", 13, LocalDate.of(2021, Month.SEPTEMBER, 21), related1));
		sets.add(new Set("Leg Anatomy", 21, LocalDate.of(2020, Month.DECEMBER, 4), related1));

		return sets;
	}
	
	/**
	 * Function to manage if a set in tableview is double clicked
	 * used to displayed the edit set scene
	 * @param event
	 */
	@FXML
	private void tableClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			System.out.println("double clicked set");
			System.out.println(tableView.getSelectionModel().getSelectedItem());
		}
	}
	
	/*
	tableView.setOnMouseClicked((MouseEvent event) -> {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            System.out.println(table.getSelectionModel().getSelectedItem());
        }
    });
    */

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

	@FXML
	private void menuBtnClick() throws Exception {
		System.out.println("clicked main menu btn");

		Parent root = FXMLLoader.load((getClass().getResource("/mainmenu/menu.fxml")));
		Scene scene = new Scene(root, Color.rgb(0, 0, 0, 0.5));
		scene.getStylesheets().add(getClass().getResource("/mainmenu/mainMenuStylesheet.css").toExternalForm());
		root.setStyle("-fx-background-color:transparent;");

		Stage window = (Stage) application.getScene().getWindow();
		window.setScene(scene);
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
