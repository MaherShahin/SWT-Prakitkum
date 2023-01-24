package interfaces;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import dbadapter.Buchung;
import dbadapter.DBFacade;
import dbadapter.Vorstellung;
import enums.Result;

public interface UKCmds {
    public Result registriereKunde(String vorname, String nachname, String email, String passwort);
    public List<Vorstellung> gibListeZukünftigenVorstellungen(Timestamp jetzt);
}

