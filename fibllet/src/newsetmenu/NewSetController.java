package newsetmenu;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import editset.EditSetController;
import flashcard.Flashcard;
import flashcard.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class NewSetController implements Initializable {
	// TODO
	// make it so when you double click, it goes to edit set menu
	// make it when you right click it brings up mini menu of edit set, rename set,
	// delete set, create quiz set

	@FXML
	AnchorPane application;

	@FXML
	private Button exitBtn;

	//@FXML
	//Button menuBtn;

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

		// set up event handler for tableview rows
		EventHandler<MouseEvent> onClick = this::handleRowMouseClick;
		tableView.setRowFactory(param -> {
			TableRow<Set> row = new TableRow<>();
			row.setOnMouseClicked(onClick);
			return row;

		});

	}

	/**
	 * Handles mouse clicks on the rows of the tableview Dehighlights selected set
	 * if empty row is clicked on 
	 * TODO: add right click menu that allows to edit set
	 * instead of having to double click row
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
				goToEditsetScene(row.getItem(), (Stage) ((Node)event.getSource()).getScene().getWindow());
				
				//consume the event
				event.consume();
			}
		}
	}
	
	/**
	 * Method to handle going to the edit set scene
	 * @throws Exception
	 */
	private void goToEditsetScene(Set rowItem, Stage st) {
		try {
			//load editSet with the row that was clicked on
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/editset/editSet.fxml"));			
			Scene scene = new Scene(loader.load());
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(scene);
			
			//make only edit set window be accessible
			stage.initOwner(st);
			stage.initModality(Modality.WINDOW_MODAL);
			
			EditSetController controller = loader.getController();
			controller.initData(rowItem);
			controller.loadFlashcards();
			
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
		/*
		Parent root;
		try {
			root = FXMLLoader.load((getClass().getResource("/editset/editSet.fxml")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	
		
		//root.setStyle("-fx-background-color:transparent;");
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/editset/editSet.css").toExternalForm());
		
		Stage window = (Stage) application.getScene().getWindow();
		window.setScene(scene);
		*/
	}

	/**
	 * Function used to construct list of all items that should be put into
	 * tableview
	 * 
	 * @return
	 */
	public ObservableList<Set> getSets() {
		ObservableList<Set> sets = FXCollections.observableArrayList();

		//create fake related quizzes for sets
		ArrayList<String> related1 = new ArrayList<String>();
		related1.add("Anatomy");
		related1.add("Quiz 1");
		
		//create fake flashcard for sets
		ArrayList<Flashcard> cards1 = new ArrayList<Flashcard>();
		cards1.add(new Flashcard("?", "!"));
		cards1.add(new Flashcard("test question", "test answer"));
		ArrayList<Flashcard> cards2 = new ArrayList<Flashcard>();
		cards2.add(new Flashcard("another test question", "another test answer"));
		cards2.add(new Flashcard("gang", "gang gang"));

		sets.add(new Set("Arm Anatomy", 13, LocalDate.of(2021, Month.SEPTEMBER, 21), related1, cards1));
		sets.add(new Set("Leg Anatomy", 21, LocalDate.of(2020, Month.DECEMBER, 4), related1, cards2));

		return sets;
	}

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
