package system;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import system.outsideinteraction.SchnittstelleVomKonsument;
import system.outsideinteraction.SchnittstelleZumKonsument;

public class EinkaufslisteSteps implements SchnittstelleZumKonsument {

	@SuppressWarnings("unused")
	private SchnittstelleVomKonsument app;	
	
	public EinkaufslisteSteps() {
		app = new EinkaufsApp(this);
	}
}





