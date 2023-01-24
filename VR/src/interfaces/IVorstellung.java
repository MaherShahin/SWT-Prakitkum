package interfaces;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import dbadapter.Buchung;
import dbadapter.Vorstellung;
import enums.Result;

public interface IVorstellung {
	
    public List<Vorstellung> get_sortierteListeVonZukünftigenVorstellungen(Timestamp jetzt);
    
    public boolean ist_istBuchbar(Timestamp termin, int sitzNummer, int reihenNummer);
    
    public Buchung vorstellungBuchen(Timestamp termin, int vid, String vorname, String nachname, double price, boolean paid, int sitzNummer,
			int reihenNummer);
        
}