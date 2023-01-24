package interfaces;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import dbadapter.Buchung;
import dbadapter.Vorstellung;
import enums.Result;

public interface RKCmds {
    public Buchung buchenKarten(Timestamp termin, Integer vid, String vorname, String nachname, int price, boolean paid, Integer sitzNummer,
			Integer reihenNummer);
    public List<Vorstellung> gibListeZukünftigenVorstellungen(Timestamp jetzt);
}