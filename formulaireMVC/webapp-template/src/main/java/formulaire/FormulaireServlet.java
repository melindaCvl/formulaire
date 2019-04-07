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

@WebServlet("/formulaire")
public class FormulaireServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // Récupération des champs (d'une manière ou d'une autre)
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordBis = req.getParameter("passwordBis");
        String approbation = req.getParameter("approbation");

        try {

            if (this.testChamps(email, password, passwordBis, approbation)) {
                Date today = new Date();
                DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

                resp.setContentType("text/html");
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().write("Votre inscription a bien été prise en compte le " + fullDateFormat.format(today)
                        + " pour l'adresse mail " + email + " ." + approbation);
            } else {
                if (this.isEmail(email)) {
                    req.setAttribute("email", email);
                } else {
                    req.setAttribute("errorEmail", "adresse email incorrecte ");
                }
                if (!this.isPassword(password)) {
                    req.setAttribute("errorPassword", "mot de passe incorrecte ");
                }
                if (!this.isSamePassword(password, passwordBis)) {
                    req.setAttribute("errorSamePassword", "les deux mots de passes sont incorrectes ");
                }
                if (this.isApprobation(approbation)) {
                    req.setAttribute("approbation", "checked");
                } else {
                    req.setAttribute("errorApprobation", "vous n'avez pas approuvé les conditions générales de ce site");
                }
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            resp.getWriter().write("erreur: " + e);
        }

    }

    /**
     * Vérifie les champs saisis par l'utilisateur
     *
     * @param email
     * @param mdp
     * @param mdpBis
     * @param approbation
     * @return
     * @throws ExceptionChamp
     */
    private void verifierChamps(String email, String mdp, String mdpBis, String approbation) throws ExceptionChamp {
        // Vérification de l'émail
        boolean estCorrect = false;
        for (int ind = 0; ind < email.length(); ind++) {
            String unChar = email.substring(ind, ind + 1);
            if (unChar.equals("@")) {
                estCorrect = true;
            }
        }

        if (!estCorrect) {
            throw new ExceptionChamp("Merci de bien vouloir saisir une adresse mail correcte.");
        }

        UtilisateurDAO interactionBDD = new UtilisateurDAO();
        if (interactionBDD.verifierExistence(email)) {
            throw new ExceptionChamp("Compte déjà existant.");
        }

        // Vérification du mot de passe
        if (mdp.length() < 8) {
            throw new ExceptionChamp("Le mot de passe doit contenir au moins 8 caractères.");
        }

        // Comparaison des 2 mots de passe
        if (!mdp.equals(mdpBis)) {
            throw new ExceptionChamp("Le mot de passe et sa confirmation ne sont pas identiques.");
        }

        // Vérification de l'approbation des conditions d'utilisateurs
        if (approbation == null) {
            throw new ExceptionChamp("Merci de bien vouloir approuver les conditions générales du site.");
        }
    }

    /**
     * @param email
     * @param mdp
     * @param mdpBis
     * @param approbation
     * @return
     */
    private boolean testChamps(String email, String mdp, String mdpBis, String approbation) {
        return isEmail(email) && isPassword(mdp) && isSamePassword(mdp, mdpBis) && isApprobation(approbation);
    }


    /**
     * start of the line
     * [_A-Za-z0-9-\\+]+	#  must start with string in the bracket [ ], must contains one or more (+)
     * (			#   start of group #1
     * \\.[_A-Za-z0-9-]+	#     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
     * )*			#   end of group #1, this group is optional (*)
     *
     * @param email
     * @return
     * @			# must contains a "@" symbol
     * [A-Za-z0-9-]+      #       follow by string in the bracket [ ], must contains one or more (+)
     * (			#         start of group #2 - first level TLD checking
     * \\.[A-Za-z0-9]+  #           follow by a dot "." and string in the bracket [ ], must contains one or more (+)
     * )*		#         end of group #2, this group is optional (*)
     * (			#         start of group #3 - second level TLD checking
     * \\.[A-Za-z]{2,}  #           follow by a dot "." and string in the bracket [ ], with minimum length of 2
     * )			#         end of group #3
     * $			#end of the line
     */
    private boolean isEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * (			# Start of group
     * (?=.*\d)		#   must contains one digit from 0-9
     * (?=.*[a-z])		#   must contains one lowercase characters
     * (?=.*[A-Z])		#   must contains one uppercase characters
     * (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
     * .		#     match anything with previous condition checking
     * {6,20}	#        length at least 6 characters and maximum of 20
     * )			# End of group
     *
     * @param password
     * @return
     */
    private boolean isPassword(String password) {
        String passwordRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,})";
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(password);
        return m.matches();
    }


    /**
     * @param password
     * @param passwordBis
     * @return
     */
    private boolean isSamePassword(String password, String passwordBis) {
        return (password.equals(passwordBis));
    }


    /**
     * @return
     */
    private boolean isApprobation() {
        return isApprobation();
    }

    /**
     * @param approbation
     * @return
     */
    private boolean isApprobation(String approbation) {
        if (approbation != null) {
            return true;
        } else {
            return false;
        }
    }

}
