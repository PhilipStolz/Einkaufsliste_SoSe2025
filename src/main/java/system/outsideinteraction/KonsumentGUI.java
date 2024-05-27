package system.outsideinteraction;

public class KonsumentGUI extends GUInterface implements SchnittstelleZumKonsument {

	private static final long serialVersionUID = 1L;
	
	public KonsumentGUI(SchnittstelleVomKonsument einkaufsApp) {
		super("Konsument");
		addActorActions(SchnittstelleVomKonsument.class, einkaufsApp);
	}	
}
