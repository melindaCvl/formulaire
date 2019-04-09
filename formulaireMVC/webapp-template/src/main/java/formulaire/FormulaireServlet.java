package formulaire;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formulaire")
public class FormulaireServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // Récupération des champs
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordBis = req.getParameter("passwordBis");
        String approbation = req.getParameter("approbation");
        
        UtilisateurDAO u = new UtilisateurDAO();
        
        try {

            if (u.testChamps(email, password, passwordBis, approbation)) {
            	succes(resp, email);
            } else {
            	echec(req, resp, u, email, password, passwordBis, approbation);
            }

        } catch (Exception e) {
            resp.getWriter().write("erreur: " + e);
        }
    }
    
    private void succes(HttpServletResponse resp, String email) {
        Date today = new Date();
        DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        
        try {
	        resp.getWriter().write("Votre inscription a bien été prise en compte le " + fullDateFormat.format(today)
	                + " pour l'adresse mail " + email + " .");
        } catch (Exception e) {}
    }
    
    private void echec(HttpServletRequest req, HttpServletResponse resp, UtilisateurDAO u, String email, String password, String passwordBis, String approbation) {
        if (u.emailCorrect(email)) {
            req.setAttribute("email", email);
        } else {
            req.setAttribute("errorEmail", "Adresse email incorrecte <br/>");
        }
        if (!u.mdpCorrect(password)) {
            req.setAttribute("errorPassword", "Mot de passe incorrect <br/>");
        }
        if (!u.mdpIdentiques(password, passwordBis)) {
            req.setAttribute("errorSamePassword", "Les deux mots de passes sont incorrects <br/>");
        }
        if (u.approbationOk(approbation)) {
            req.setAttribute("approbation", "checked");
        } else {
            req.setAttribute("errorApprobation", "Vous n'avez pas approuvé les conditions générales de ce site <br/>");
        }
        
        try {
        	req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {}
        
    }
}
