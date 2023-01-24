package dbadapter;

import java.sql.Timestamp;

/**
 * Class representing a booking
 * 
 * @author swe.uni-due.de
 *
 */
public class Buchung {
	 private int id;
	 private Timestamp erstellungsDate;
	 private Timestamp termin;
	 private String vorname;
	 private String nachname;
	 private double price;
	 private boolean paid;
	 private int vid;
	 private int sitzNummer;
	 private int reihenNummer;

	 public Buchung(int id, Timestamp erstellungsDate, Timestamp termin, String vorname, String nachname, double price, boolean paid, int vid, int sitzNummer, int reihenNummer) {
		    this.id = id;
		    this.erstellungsDate = erstellungsDate;
		    this.termin = termin;
		    this.vorname = vorname;
		    this.nachname = nachname;
		    this.price = price;
		    this.paid = paid;
		    this.vid = vid;
		    this.sitzNummer = sitzNummer;
		    this.reihenNummer = reihenNummer;
		}

		public int getId() {
		    return id;
		}

		public void setId(int id) {
		    this.id = id;
		}

		public Timestamp getErstellungsDate() {
		    return erstellungsDate;
		}

		public void setErstellungsDate(Timestamp erstellungsDate) {
		    this.erstellungsDate = erstellungsDate;
		}

		public Timestamp getTermin() {
		    return termin;
		}

		public void setTermin(Timestamp termin) {
		    this.termin = termin;
		}

		public String getVorname() {
		    return vorname;
		}

		public void setVorname(String vorname) {
		    this.vorname = vorname;
		}

		public String getNachname() {
		    return nachname;
		}

		public void setNachname(String nachname) {
		    this.nachname = nachname;
		}

		public double getPrice() {
		    return price;
		}

		public void setPrice(double preis) {
		    this.price = preis;
		}

		public boolean isPaid() {
		    return paid;
		}

		public void setPaid(boolean bezahlt) {
		    this.paid = bezahlt;
		}

		public int getVid() {
		    return vid;
		}

		public void setVid(int vid) {
		    this.vid = vid;
		}

		public int getSitzNummer() {
		    return sitzNummer;
		}

		public void setSitzNummer(int sitzNummer) {
		    this.sitzNummer = sitzNummer;
		}

		public int getReihenNummer() {
		    return reihenNummer;
		}

		public void setReihenNummer(int reihenNummer) {
		    this.reihenNummer = reihenNummer;
		}
		    
}
