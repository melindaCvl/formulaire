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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String mdp = req.getParameter("password");
		String mdpBis = req.getParameter("passwordBis");
		String approbation = req.getParameter("approbation");
		
		boolean champCorrects;
		
		try {
			champCorrects = verifierChamps();
		} catch (ExceptionChamp exc) {
			
		}
	}
	
	private boolean verifierChamps() throws ExceptionChamp {
		return true;
	}
}
