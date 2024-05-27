package system;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import system.outsideinteraction.SchnittstelleVomKonsument;
import system.outsideinteraction.SchnittstelleZumKonsument;

import java.util.Collection;

public class EinkaufslisteSteps implements SchnittstelleZumKonsument {

	@SuppressWarnings("unused")
	private final SchnittstelleVomKonsument app;
	private Collection<String> einkaufsliste = null;
	
	public EinkaufslisteSteps() {
		app = new EinkaufsApp(this);
	}

	@SuppressWarnings("EmptyMethod")
    @Given("eine leere Einkaufsliste")
	public void eine_leere_einkaufsliste() {
		// intentionally left empty, nothing has to be done to get a leere Einkaufsliste
	}

	@When("ich schreibe {string} in die Einkaufsliste")
	public void ich_schreibe_in_die_einkaufsliste(String eintrag) {
		app.schreibeInEinkaufsliste(eintrag);
	}

	@Then("die Einkaufsliste sollte den Eintrag {string} enthalten.")
	public void die_einkaufsliste_sollte_den_eintrag_enthalten(String eintrag) {
		assertNotNull(einkaufsliste);
		assertTrue(einkaufsliste.contains(eintrag));
	}

	@Override
	public void zeigeEinkaufsliste(Collection<String> einkaufsliste) {
        this.einkaufsliste = einkaufsliste;
	}
}





