import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.GebruikersModel;
import models.Notflix;

/**
 * 
 */

/**
 * @author chris
 *
 */
@WebListener
public class NotflixServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 Notflix notflix = new Notflix();
		 arg0.getServletContext().setAttribute("notflix",notflix);
		 
		 notflix.addGebruiker(new GebruikersModel("Peerhoofd", "", "Jopie", "nick", "test123"));
		 notflix.addGebruiker(new GebruikersModel("Test", "", "Johnny", "gebruiker", "Wachtwoord"));
		 notflix.addGebruiker(new GebruikersModel("Peerhoofd", "tussenvoegsel", "Jopie", "nick", "test123"));
	}

}
