package timer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import application.KWApplication;
import dbadapter.DBFacade;
import dbadapter.Vorstellung;

/**
 * Timer class to call the method archivier in the application. Main method
 * can be executed in a scheduled way.
 * 
 * @author swe.uni-due.de
 *
 *	Class should be used to regulary check Vorstellungen from the DB and archives automatically all that are older 
 *	than the current date. 
 *	Frequency should be reasonable
 */


public class Timer {
	
    private static final int FIVE_MINUTES = 5 * 60 * 1000;	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Timer instance; 

    private Timer() {}

    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    public static void archiviere(List<Vorstellung> vorstellungen) {
    	
        Timestamp jetzt = new Timestamp(System.currentTimeMillis());
        
        for (Vorstellung vorstellung : vorstellungen) {
            if (vorstellung.getTermin().after(jetzt)) {
                vorstellung.setArchiviert(true);
                KWApplication.updateVorstellung(vorstellung);
                System.out.println("Vorstellung with ID: " + vorstellung.getId() + " has been archived.");
            }
        }
    }

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            public void run() {
                archiviere();
                System.out.println("All vorstellungen that are older than current date have been archived.");
            }
        };

        Timer timer = Timer.getInstance();

        // Run the task every 15 minutes
        timer.schedule(task, FIFTEEN_MINUTES);

        System.out.println("Timer started at: " + dateFormat.format(new Date()));
    }
}
