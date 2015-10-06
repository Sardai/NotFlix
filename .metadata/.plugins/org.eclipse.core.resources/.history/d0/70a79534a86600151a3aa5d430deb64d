import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import models.Notflix;

/**
 * 
 */

/**
 * @author chris
 *
 */
public class NotflixServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 Notflix notflix = new Notflix();
		 arg0.getServletContext().setAttribute("notflix",notflix);
	}

}
