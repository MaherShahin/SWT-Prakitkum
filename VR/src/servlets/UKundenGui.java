package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbadapter.DBFacade;
import enums.Result;

public class UKundenGui extends HttpServlet {

    private DBFacade dbfacade = DBFacade.getInstance();
    private RequestDispatcher rd;

    public void registriereen(String vorname, String nachname, String email, String passwort, HttpServletRequest req, HttpServletResponse res) {
        boolean emailExists = dbfacade.ist_istKundeExistiert(email);
        if (emailExists) {
            zeigeFehlerR(req, res);
        } else {
            Result success = dbfacade.speichereKundenDaten(vorname, nachname, email, passwort);
            if (success == Result.OK) {
                zeigeOkR(req, res);
            } else {
                zeigeFehlerR(req, res);
            }
        }
    }

    public void zeigeOkR(HttpServletRequest req, HttpServletResponse res) {
        rd = req.getRequestDispatcher("registrierenSuccess.jsp");
        try {
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void zeigeFehlerR(HttpServletRequest req, HttpServletResponse res) {
        rd = req.getRequestDispatcher("registrierenError.jsp");
        try {
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    
}