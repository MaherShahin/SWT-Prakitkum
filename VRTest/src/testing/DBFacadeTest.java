package testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import dbadapter.Buchung;
import dbadapter.Configuration;
import dbadapter.DBFacade;
import dbadapter.KundenDaten;
import dbadapter.Vorstellung;
import enums.Result;
import junit.framework.TestCase;

public class DBFacadeTest extends TestCase {
	
	private DBFacade facade = DBFacade.getInstance();
	
	@Before
	public void setUp() {
		
		Vorstellung testVorstellung1 = new Vorstellung(1, "TestVorstellung", 120, 1, Timestamp.valueOf("2023-03-04 01:01:00"), false);
		Vorstellung testVorstellung2 = new Vorstellung(2, "TestVorstellung2", 120, 2, Timestamp.valueOf("2023-03-05 03:04:02"), false);
		Vorstellung testVorstellung3 = new Vorstellung(3, "TestVorstellung3", 120, 3, Timestamp.valueOf("2023-06-04 02:03:10"), false);
		Vorstellung testVorstellungArchive = new Vorstellung(4, "TestVorstellungArchive", 120, 1, Timestamp.valueOf("2022-01-01 00:00:00"), true);

		KundenDaten testKunde = new KundenDaten(1, "John", "Doe", "johndoe@email.com", "password123");

		// SQL statements
		
		String sqlCleanDB = "DELETE FROM Vorstellung, Buchung, KundenDaten";
		
		String sqlCreateTableVorstellungen = "CREATE TABLE Vorstellung (id int(11) NOT NULL AUTO_INCREMENT, titel varchar(255) "
				+ "NOT NULL, dauer float NOT NULL, saalNummer int(11) NOT NULL, termin timestamp NOT NULL, istArchiv tinyint(1) "
				+ "NOT NULL, PRIMARY KEY (id))";
		
		String sqlCreateTableKunden = "CREATE TABLE KundenDaten (id int(11) NOT NULL AUTO_INCREMENT, vorname varchar(255) NOT NULL, nachname varchar(255) NOT NULL, email varchar(255) NOT NULL, password varchar(255) NOT NULL, PRIMARY KEY (id))";
		
		String sqlInsertVorstellung = "INSERT INTO Vorstellung (id,titel,dauer,saalNummer,termin,istArchiv) VALUES (?,?,?,?,?,?)";
		
	    String sqlInsertKunde = "INSERT INTO KundenDaten (id, vorname, nachname, email, password) VALUES (?, ?, ?, ?, ?)";

	    // Perform database updates
	    try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
//	        try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
//	            psClean.executeUpdate();
//	        }
	        try (PreparedStatement psCreateVorstellungen = connection.prepareStatement(sqlCreateTableVorstellungen)) {
	        	psCreateVorstellungen.executeUpdate();
	        }
	        try (PreparedStatement psCreateKunden = connection.prepareStatement(sqlCreateTableKunden)) {
	        psCreateKunden.executeUpdate();
	        }
	        // insert test data into tables
	        try (PreparedStatement psInsertVorstellung = connection.prepareStatement(sqlInsertVorstellung)) {
	        psInsertVorstellung.setInt(1, testVorstellung1.getId());
	        psInsertVorstellung.setString(2, testVorstellung1.getTitel());
	        psInsertVorstellung.setFloat(3, testVorstellung1.getDauer());
	        psInsertVorstellung.setInt(4, testVorstellung1.getSaalNummer());
	        psInsertVorstellung.setTimestamp(5, testVorstellung1.getTermin());
	        psInsertVorstellung.setBoolean(6, testVorstellung1.getIstArchiv());
	        psInsertVorstellung.executeUpdate();
	        }
	        try (PreparedStatement psInsertVorstellung = connection.prepareStatement(sqlInsertVorstellung)) {
	        psInsertVorstellung.setInt(1, testVorstellung2.getId());
	        psInsertVorstellung.setString(2, testVorstellung2.getTitel());
	        psInsertVorstellung.setFloat(3, testVorstellung2.getDauer());
	        psInsertVorstellung.setInt(4, testVorstellung2.getSaalNummer());
	        psInsertVorstellung.setTimestamp(5, testVorstellung2.getTermin());
	        psInsertVorstellung.setBoolean(6, testVorstellung2.getIstArchiv());
	        psInsertVorstellung.executeUpdate();
	        }
	        try (PreparedStatement psInsertVorstellung = connection.prepareStatement(sqlInsertVorstellung)) {
	        psInsertVorstellung.setInt(1, testVorstellung3.getId());
	        psInsertVorstellung.setString(2, testVorstellung3.getTitel());
	        psInsertVorstellung.setFloat(3, testVorstellung3.getDauer());
	        psInsertVorstellung.setInt(4, testVorstellung3.getSaalNummer());
	        psInsertVorstellung.setTimestamp(5, testVorstellung3.getTermin());
	        psInsertVorstellung.setBoolean(6, testVorstellung3.getIstArchiv());
	        psInsertVorstellung.executeUpdate();
	        }
	        try (PreparedStatement psInsertVorstellung = connection.prepareStatement(sqlInsertVorstellung)) {
	        psInsertVorstellung.setInt(1, testVorstellungArchive.getId());
	        psInsertVorstellung.setString(2, testVorstellungArchive.getTitel());
	        psInsertVorstellung.setFloat(3, testVorstellungArchive.getDauer());
	        psInsertVorstellung.setInt(4, testVorstellungArchive.getSaalNummer());
	        psInsertVorstellung.setTimestamp(5, testVorstellungArchive.getTermin());
	        psInsertVorstellung.setBoolean(6, testVorstellungArchive.getIstArchiv());
	        psInsertVorstellung.executeUpdate();
	        }
	        try (PreparedStatement psInsertKunden = connection.prepareStatement(sqlInsertKunde)) {
	        	psInsertKunden.setInt(1, testKunde.getId());
	        	psInsertKunden.setString(2, testKunde.getVorname());
	        	psInsertKunden.setString(3, testKunde.getNachname());
	        	psInsertKunden.setString(4, testKunde.getEmail());
	        	psInsertKunden.setString(5, testKunde.getPassword());
	        	psInsertKunden.executeUpdate();
	        }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }
	
	@After
	public void tearDown() {

		// SQL statements 
		String sqlCleanDB = "DROP TABLE IF EXISTS Buchung, Vorstellung, KundenDaten";

		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	        	
	// Tests if getSortierteListeVonZukünftigenVorstellungen returns only vorstellungen with date after now AND are not archived!
	@org.junit.Test
	public void testGetSortierteListeVonZukünftigenVorstellungen() {
		Timestamp jetzt = new Timestamp(System.currentTimeMillis());
		List<Vorstellung> vorstellungen = facade.get_sortierteListeVonZukünftigenVorstellungen(jetzt);
		
		assertNotNull(vorstellungen);
		
		System.out.println(vorstellungen.size());
		assertTrue(vorstellungen.size() > 0);
		
		for (Vorstellung v : vorstellungen) {
			assertTrue(v.getTermin().after(jetzt));
			assertFalse(v.getIstArchiv());
		}
		
		assertTrue(vorstellungen.size() == 3);
		
		assertTrue(vorstellungen.get(0).getId()==1);
		assertTrue(vorstellungen.get(0).getSaalNummer()==1);
		assertTrue(vorstellungen.get(0).getIstArchiv() == false);

		
		assertTrue(vorstellungen.get(1).getId()==2);
		assertTrue(vorstellungen.get(1).getSaalNummer()==2);
		assertTrue(vorstellungen.get(1).getIstArchiv() == false);
		
		
		//Verify other return values 
		
	}

	// Tests if speichereKundenDaten() saves a user with a unique email address and 
	// whether the same unique email address gets rejected when passed a second time
	// speichereKundenDaten() returns a Result enum representing OK|FAIL 
	// depending on the response of the DB
	@org.junit.Test
	public void testSpeichereKundenDaten() {
		String vorname = "Xin";
		String nachname = "Zhao";
		String email = "Xin.Zhao@uni-due.de";
		String password = "password123";
		
		// Test for successful customer data storage
		Result res = facade.speichereKundenDaten(vorname, nachname, email, password);
		assertTrue(res == Result.OK);
		
		// Test for failed customer data storage
		// Save a user that is already saved, 
		res = facade.speichereKundenDaten("Xin2", "Zhao2", email, password);
		assertTrue(res == Result.FAIL);
		
		//Verify other return values 
		// None I think herer, we return a result not a Kunde object
	}


}


