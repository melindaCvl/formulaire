package formulaire;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ExceptionChamp;

@WebServlet("/formulaire")
public class FormulaireServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		
		// Récupération des champs (d'une manière ou d'une autre)
		String email = req.getParameter("email");
		String mdp = req.getParameter("password");
		String mdpBis = req.getParameter("passwordBis");
		String approbation = req.getParameter("approbation");		
		try {
			verifierChamps(email, mdp, mdpBis, approbation);
		} catch (ExceptionChamp exc) {
			
		}
	}
	
	/**
	 * Vérifie les champs saisis par l'utilisateur
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
		for(int ind = 0; ind < email.length(); ind++) {
			String unChar = email.substring(ind, ind + 1);
			if(unChar.equals("@")) {
				estCorrect = true;
			}
		}
		
		if (!estCorrect) {
			throw new ExceptionChamp("Merci de bien vouloir saisir une adresse mail correcte.");
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
}
