package application;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbadapter.Buchung;
import dbadapter.DBFacade;
import dbadapter.Vorstellung;
import enums.Result;
import interfaces.RKCmds;
import interfaces.UKCmds;
import timer.Timer;

public class KWApplication implements UKCmds, RKCmds {

	private static KWApplication instance;
	private static DBFacade db;
	private static Timestamp now;

	public static KWApplication getInstance() {
	    if (instance == null) {
	        instance = new KWApplication();
	        if(db == null) {
	        	db = DBFacade.getInstance();
	        }
	    }
	    return instance;
	}

	public Result registriereKunde(String vorname, String nachname, String email, String passwort) {
	    if (db.ist_istKundeExistiert(email)) {
	        return Result.FAIL;
	    } else {
	        try {
				return db.speichereKundenDaten(vorname, nachname, email, passwort);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		return Result.FAIL;
	}

	public List<Vorstellung> gibListeZukünftigenVorstellungen() {
	    return db.get_sortierteListeVonZukünftigenVorstellungen(new Timestamp(System.currentTimeMillis()));
	}


	@Override
	public Buchung buchenKarten(Timestamp termin, Integer vid, String vorname, String nachname, int price, boolean paid, Integer sitzNummer,
			Integer reihenNummer) {
	    if (db.ist_istBuchbar(termin, sitzNummer, reihenNummer)) {
	        return db.vorstellungBuchen(termin,vid,vorname,nachname,price,paid,sitzNummer,reihenNummer);
	    } else {
	        return null;
	    }
	}

	@Override
	public List<Vorstellung> gibListeZukünftigenVorstellungen(Timestamp jetzt) {
	    return db.get_sortierteListeVonZukünftigenVorstellungen(new Timestamp(System.currentTimeMillis()));
	}
	
	public void ueberpfuefeVorstellung() {
	    Timer.getInstance().archiviere(db.getTobeArchivedVorstellungen(new Timestamp(System.currentTimeMillis())));
	}


}

