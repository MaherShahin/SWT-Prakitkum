package interfaces;

import enums.Result;

public interface IKundenDaten {
	
	public Result speichereKundenDaten(String vorname, String nachname, String email, String password);
	
	public boolean ist_istKundeExistiert(String email);

}