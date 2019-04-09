package formulaire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilisateurDAO {
	
	public boolean emailCorrect(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);
        return m.matches() && !estExistant(email);
	}
	
	private boolean estExistant(String email) {
		return true;
	}
	
	public boolean mdpCorrect(String mdp) {
		return mdp.length() >= 8;
	}
	
	public boolean mdpIdentiques(String mdp, String confirmation) {
		return (mdp.equals(confirmation));
	}
	
	public boolean approbationOk(String approbation) {
        if (approbation != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean testChamps(String email, String mdp, String mdpBis, String approbation) {
        return emailCorrect(email) && mdpCorrect(mdp) && mdpIdentiques(mdp, mdpBis) && approbationOk(approbation);
    }
}
