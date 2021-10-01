package flashcard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Set {
	private SimpleStringProperty name;
	private SimpleIntegerProperty cardCount;
	private LocalDate creationDate;
	private ArrayList<String> relatedQuizzes;

	public Set(String name, int count, LocalDate date, ArrayList<String> relatedQuizzes) {
		this.name = new SimpleStringProperty(name);
		this.cardCount = new SimpleIntegerProperty(count);
		this.creationDate = date;
		// figure out how to pass in a string and store in arraylist of
		// simplestringproperties
		this.relatedQuizzes = relatedQuizzes;
	}

	public String getSetName() {
		return name.get();
	}

	public Integer getCardCount() {
		return cardCount.get();
	}

	public String getCreationDate() {
		return creationDate.getMonth().toString().charAt(0)
				+ creationDate.getMonth().toString().substring(1).toLowerCase() + " " + creationDate.getDayOfMonth()
				+ ", " + creationDate.getYear();
	}

	public String getRelatedQuizzes() {
		String full = "";

		for (String related : relatedQuizzes) {
			full += " " + related;
		}
		return full;
	}

}
