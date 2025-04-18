package system;

import system.outsideinteraction.KonsumentGUI;
import system.outsideinteraction.SchnittstelleVomKonsument;
import system.outsideinteraction.SchnittstelleZumKonsument;

// Erste Änderung

public class EinkaufsApp implements SchnittstelleVomKonsument {

	@SuppressWarnings("unused")
	private SchnittstelleZumKonsument konsument;
	
	public EinkaufsApp() {
		
	}
	
	public EinkaufsApp(SchnittstelleZumKonsument konsument) {
		this.konsument = konsument;
	}

	public static void main(String[] args) {
		EinkaufsApp einkaufsApp = new EinkaufsApp(); 
		einkaufsApp.setKonsument(new KonsumentGUI(einkaufsApp));
	}

	private void setKonsument(SchnittstelleZumKonsument konsument) {
		this.konsument = konsument;
	}

}
