package dbadapter;

public class KundenDaten {
	private int id;
	private String vorname;
	private String nachname;
	private String email;
	private String password;

	public KundenDaten(int id, String vorname, String nachname, String email, String password) {
	    this.id = id;
	    this.vorname = vorname;
	    this.nachname = nachname;
	    this.email = email;
	    this.password = password;
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
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

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	


}
