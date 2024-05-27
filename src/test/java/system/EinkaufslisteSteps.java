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

	@Given("eine leere Einkaufsliste")
	public void eine_leere_einkaufsliste() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("ich schreibe {string} in die Einkaufsliste")
	public void ich_schreibe_in_die_einkaufsliste(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("die Einkaufsliste sollte den Eintrag {string} enthalten.")
	public void die_einkaufsliste_sollte_den_eintrag_enthalten(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}





