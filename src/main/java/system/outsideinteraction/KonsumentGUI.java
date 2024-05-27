package system.outsideinteraction;

import java.util.Collection;

public class KonsumentGUI extends GUInterface implements SchnittstelleZumKonsument {

	private static final long serialVersionUID = 1L;
	
	public KonsumentGUI(SchnittstelleVomKonsument einkaufsApp) {
		super("Konsument");
		addActorActions(SchnittstelleVomKonsument.class, einkaufsApp);
	}

	@Override
	public void zeigeEinkaufsliste(Collection<String> einkaufsliste) {
		super.displayIncomingSystemAction("Einkaufsliste: " + einkaufsliste.toString());
	}
}
