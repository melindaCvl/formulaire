package formulaire;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ExceptionChamp;
import jdk.jshell.execution.Util;

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
                Date today = new Date();
                DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

                resp.setContentType("text/html");
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().write("Votre inscription a bien été prise en compte le " + fullDateFormat.format(today)
                        + " pour l'adresse mail " + email + " .");
            } else {
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
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            resp.getWriter().write("erreur: " + e);
        }
    }
}
