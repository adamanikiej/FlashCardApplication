package flashcard;

import javafx.beans.property.SimpleStringProperty;

public class Flashcard {
	private SimpleStringProperty question;
	private SimpleStringProperty answer;
	
	public Flashcard(String q, String a) {
		this.question = new SimpleStringProperty(q);
		this.answer = new SimpleStringProperty(a);
	}
	
	public String getQuestion() {
		return question.get();
	}
	
	public void setQuestion(String q) {
		question = new SimpleStringProperty(q);
	}
	
	public String getAnswer() {
		return answer.get();
	}
	
	public void setAnswer(String a) {
		answer = new SimpleStringProperty(a);
	}
	
}
