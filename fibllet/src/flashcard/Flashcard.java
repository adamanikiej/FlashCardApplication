package flashcard;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class Flashcard {
	private TextField question;
	private TextField answer;
	
	/**
	 * Flashcard Constructor
	 * @param q, String for the question field of flashcard
	 * @param a, String for the answer field of flashcard
	 */
	public Flashcard(String q, String a) {
		this.question = new TextField(q);
		this.answer = new TextField(a);
	}
	
	public TextField getQuestion() {
		return question;
	}
	
	public void setQuestion(String q) {
		question = new TextField(q);
	}
	
	public TextField getAnswer() {
		return answer;
	}
	
	public void setAnswer(String a) {
		answer = new TextField(a);
	}
	
}
