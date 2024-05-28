package system;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import system.outsideinteraction.SchnittstelleVomKonsument;
import system.outsideinteraction.SchnittstelleZumKonsument;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

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

	@Given("folgende Einkaufsliste:")
	public void folgende_einkaufsliste(io.cucumber.datatable.DataTable vorgegebeneEinkaufsliste) {
		int listLength = vorgegebeneEinkaufsliste.height();
		for(int idx = 0; idx < listLength; idx++) {
			String eintrag = vorgegebeneEinkaufsliste.cell(idx, 0);
			app.schreibeInEinkaufsliste(eintrag);
		}
	}

	@Then("die Einkaufsliste sollte genau folgende Einträge in beliebiger Reihenfolge enthalten:")
	public void die_einkaufsliste_sollte_genau_folgende_eintraege_in_beliebiger_reihenfolge_enthalten(io.cucumber.datatable.DataTable vorgegebeneEinkaufsliste) {
		int listLength = vorgegebeneEinkaufsliste.height();

		LinkedList<String> einkaufslisteSortiert = new LinkedList<>(einkaufsliste);
		Collections.sort(einkaufslisteSortiert);

		LinkedList<String> einkaufslisteErwartet = new LinkedList<>();

		for(int idx = 0; idx < listLength; idx++) {
			String eintrag = vorgegebeneEinkaufsliste.cell(idx, 0);
			einkaufslisteErwartet.add(eintrag);
		}
        Collections.sort(einkaufslisteErwartet);

		assertEquals(einkaufslisteErwartet, einkaufslisteSortiert);
	}

	@Override
	public void zeigeEinkaufsliste(Collection<String> einkaufsliste) {
        this.einkaufsliste = einkaufsliste;
	}
}





