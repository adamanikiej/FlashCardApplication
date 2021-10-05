package editset;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
	
	/**
	 * stores the name of the set
	 */
	@FXML
	private TextField name; 
	
	/**
	 * stores the set which was clicked in previous menu
	 */
	private Set clickedSet;
	
	/**
	 * local list of the set's flashcards, does not mutate actual set
	 * used for displaying flashcards in tableview
	 */
	ObservableList<Flashcard> cards = FXCollections.observableArrayList();
	
	
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
	 * runs after initialize to pass parameters between windows
	 * @param set
	 */
	public void initData(Set set) {
		name.setText(set.getSetName());
		this.clickedSet = set;
	}
	
	/**
	 * Function ran after initData so flashcards are loaded into Tableview
	 */
	public void loadFlashcards() {
		// populate set with data for testing
		cards.addAll(clickedSet.getFlashcards());
		tableView.setItems(cards);
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
	
	/**
	 * Button used to add an additional card to a set in the tableview
	 */
	@FXML
	private void addCardBtnClick() {
		System.out.println("clicked add card btn");
		cards.add(new Flashcard("", ""));
	}
	
	@FXML
	private void exitBtnClick() {
		//TODO make it so alert is only displayed if changes are made
		
		//display alert if they want to exit without saving
		ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.YES);
		ButtonType no = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
		ButtonType back = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert a = new Alert(AlertType.CONFIRMATION, "Want to save your changes to the set?", save, no, back);
		a.setTitle("Exit without Saving?");
		a.setHeaderText(null);
		//wait till alert is answered
		Optional<ButtonType> result = a.showAndWait();

		//based on answer, either exit or go back
		if (result.get().getButtonData() == ButtonType.YES.getButtonData()) { //save
			saveSet();
		} else if (result.get().getButtonData() == ButtonType.CANCEL.getButtonData()) { //go back to set editor
			a.close();
			return;
		}
		//save and don't save run this code below
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
		//save changes made to set
		saveSet();
		
		
		//exiting set editor window
		Node source = (Node) event.getSource();
		Stage stg = (Stage) source.getScene().getWindow();
		stg.close();
	}
	
	/**
	 * Function used to save the changes made during Set Editor
	 */
	private void saveSet() {
		//saving changes
		clickedSet.setSetName(name.getText());
		clickedSet.setCardCount(cards.size());
		ArrayList<Flashcard> temp = new ArrayList<Flashcard>();
		temp.addAll(cards);
		clickedSet.setFlashcards(temp);
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
