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

	@Given("folgende Einkaufsliste:")
	public void folgende_einkaufsliste(io.cucumber.datatable.DataTable vorgegebeneEinkaufsliste) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Then("die Einkaufsliste sollte genau folgende Einträge in beliebiger Reihenfolge enthalten:")
	public void die_einkaufsliste_sollte_genau_folgende_eintraege_in_beliebiger_reihenfolge_enthalten(io.cucumber.datatable.DataTable vorgegebeneEinkaufsliste) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Override
	public void zeigeEinkaufsliste(Collection<String> einkaufsliste) {
        this.einkaufsliste = einkaufsliste;
	}
}





