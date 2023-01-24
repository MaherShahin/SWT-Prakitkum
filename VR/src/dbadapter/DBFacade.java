package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import interfaces.IVorstellung;
import interfaces.IKundenDaten;
import enums.Result;

public class DBFacade implements IVorstellung, IKundenDaten {
	private static DBFacade instance;
	private Connection con;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
		Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		try {
		con = DriverManager.getConnection("jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
		+ Configuration.getPort() + "/" + "aufgabe_praktikum", Configuration.getUser(), Configuration.getPassword());
		} catch (SQLException e) {
		e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	/**

	Set the instance of the DBFacade to the given dbfacade
	@param dbfacade
	*/
	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	/**

	Fetches all future shows from the database and returns them in a list sorted by date
	@param jetzt current timestamp
	@return list of all future shows sorted by date
	*/
    public List<Vorstellung> get_sortierteListeVonZukünftigenVorstellungen(Timestamp jetzt) {
        List<Vorstellung> vorstellungen = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Vorstellung WHERE termin >= ?");
            stmt.setTimestamp(1, jetzt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vorstellung v = new Vorstellung(rs.getInt("id"), rs.getString("titel"), rs.getFloat("dauer"), rs.getInt("saalNummer"), rs.getTimestamp("termin"), rs.getBoolean("istArchiv"));
                vorstellungen.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vorstellungen;
    }
    
    /**
    Saves the customer data to the database
    @param vorname first name of the customer
    @param nachname last name of the customer
    @param email email address of the customer
    @param password password of the customer
    @return Result.OK if the data was saved successfully, Result.FAIL otherwise
    */
    public Result speichereKundenDaten(String vorname, String nachname, String email, String password) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO KundenDaten (vorname, nachname, email, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, vorname);
            stmt.setString(2, nachname);
            stmt.setString(3, email);
            stmt.setString(4, password);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0) {
                return Result.OK;
            } else {
                return Result.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.FAIL;
        }
    }
    
    /**

    Check if a customer with the given email address exists in the database
    @param email email address of the customer
    @return true if the customer exists, false otherwise
    */
    public boolean ist_istKundeExistiert(String email) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM KundenDaten WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            } else {
            	return false;
            	}
            } 	
        catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        	}
    }         

    /**
     * Check if a seat (given by its seat and row number) is available for booking for a given performance at a given time
     * @param termin timestamp of the performance
     * @param sitzNummer seat number of the seat
     * @param reihenNummer row number of the seat
     * @return true if the seat is available for booking, false otherwise
     * */
    
    public boolean ist_istBuchbar(Timestamp termin, int sitzNummer, int reihenNummer) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Buchung WHERE termin = ? AND sitzNummer = ? AND reihenNummer = ?");
            stmt.setTimestamp(1, termin);
            stmt.setInt(2, sitzNummer);
            stmt.setInt(3, reihenNummer);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	return false;
            } else {
            return true;
            }
            } catch (SQLException e) {
            e.printStackTrace();
            return false;
            }
            }

    /**

    Retrieve customer data for a given email address
    @param email email address of the customer
    @return customer data as a KundenDaten object or null if no customer with the given email address exists
    */
    public KundenDaten get_kundenDaten(String email) {
    try {
	    PreparedStatement stmt = con.prepareStatement("SELECT * FROM KundenDaten WHERE email = ?");
	    stmt.setString(1, email);
	    ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    KundenDaten kundenDaten = new KundenDaten(rs.getInt("id"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("email"), rs.getString("password"));
	    return kundenDaten;
	    } else {
	    return null;
	    }
	    } catch (SQLException e) {
	    e.printStackTrace();
	    return null;
	    }
	    }
	    }
    

	// had to add this -> very useful utility function / NOT METNIONED IN DOCS OR AS CLASS METHOD -> for archiving cron job
	public List<Vorstellung> getTobeArchivedVorstellungen(Timestamp jetzt) {
		List<Vorstellung> vorstellungen = new ArrayList<>();
		try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM Vorstellungen WHERE termin < ?");
				stmt.setTimestamp(1, jetzt);
				ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Vorstellung v = new Vorstellung(rs.getInt("id"), rs.getString("titel"), rs.getFloat("dauer"), rs.getInt("saalNummer"), rs.getTimestamp("termin"), rs.getBoolean("istArchiv"));
				vorstellungen.add(v);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return vorstellungen;
	}

}
