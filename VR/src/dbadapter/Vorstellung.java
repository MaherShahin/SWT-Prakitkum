package dbadapter;

import java.sql.Timestamp;

public class Vorstellung {
	
	private int id;
	private String titel;
	private float dauer;
	private int saalNummer;
	private Timestamp termin;
	private boolean istArchiv;
	
	public Vorstellung(int id, String titel, float dauer, int saalNummer, Timestamp termin, boolean istArchiv) {
		this.id = id;
		this.titel = titel;
		this.dauer = dauer;
		this.saalNummer = saalNummer;
		this.termin = termin;
		this.istArchiv = istArchiv;
	}
	
	public int getId() {
		return id;
		}
	public String getTitel() {
		return titel;
	}
	public float getDauer() {
		return dauer;
	}
	public int getSaalNummer() {
		return saalNummer;
	}
	public Timestamp getTermin() {
		return termin;
	}
	public boolean getIstArchiv() {
		return istArchiv;
	}
		
}
