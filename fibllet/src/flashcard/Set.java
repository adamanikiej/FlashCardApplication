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

	/**
	 * Constructor Used for displaying Sets in tableview
	 * 
	 * @param name
	 * @param count
	 * @param date
	 * @param relatedQuizzes
	 */
	public Set(String name, int count, LocalDate date, ArrayList<String> relatedQuizzes) {
		this.name = new SimpleStringProperty(name);
		this.cardCount = new SimpleIntegerProperty(count);
		this.creationDate = date;
		// figure out how to pass in a string and store in arraylist of
		// simplestringproperties
		this.relatedQuizzes = relatedQuizzes;
	}

	/**
	 * Getter for tableview
	 * 
	 * @return String of set name
	 */
	public String getSetName() {
		return name.get();
	}

	/**
	 * Getter for tableview
	 * 
	 * @return Integer of card count in set
	 */
	public Integer getCardCount() {
		return cardCount.get();
	}

	/**
	 * Getter for tableview
	 * 
	 * @return String of creation date of set "Month Day, Year" format
	 */
	public String getCreationDate() {
		return creationDate.getMonth().toString().charAt(0)
				+ creationDate.getMonth().toString().substring(1).toLowerCase() + " " + creationDate.getDayOfMonth()
				+ ", " + creationDate.getYear();
	}

	/**
	 * Getter for tableview
	 * 
	 * @return String of related quizzes for given set "relatedQuiz1, relatedQuiz2"
	 *         format
	 */
	public String getRelatedQuizzes() {
		String full = "";

		for (int i = 0; i < relatedQuizzes.size() - 1; i++) {
			full += relatedQuizzes.get(i) + ", ";
		}
		if (relatedQuizzes.size() > 0) {
			full += relatedQuizzes.get(relatedQuizzes.size() - 1);
		}
		return full;
	}

	@Override
	public String toString() {
		String result = "";
		result += "Set Name: " + getSetName() + "\nCard Count: " + getCardCount() + "\nCreation Date: "
				+ getCreationDate() + "\nRelated Quizzes: " + getRelatedQuizzes();
		return result;
	}

}
