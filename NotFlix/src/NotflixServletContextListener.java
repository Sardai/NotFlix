import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.Movie;
import models.Gebruiker;
import models.Notflix;
import models.Rating;

/**
 * 
 */

/**
 * @author chris,viradj
 *	servlet context listener for notflix.
 */
@WebListener
public class NotflixServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 Notflix notflix = new Notflix();
		 
		 List<Movie> movies = notflix.getMovies();
		 movies.add(new Movie("tt1392190", "Mad Max: Fury Road", "2015-06-12", 120, "George Miller", "A woman rebels against a tyrannical ruler in post apocalyptic Australia in search for her homeland with the help of a group of female prisoners, a psychotic worshiper, and a drifter named Max."));
		 movies.add(new Movie("tt1340138", "Terminator Genisys","2015-07-01",126,"Alan Taylor","When John Connor, leader of the human resistance, sends Sgt. Kyle Reese back to 1984 to protect Sarah Connor and safeguard the future, an unexpected turn of events creates a fractured timeline."));
		 movies.add(new Movie("tt0848228", "The Avengers","2012-04-25",143,"Joss Whedon","Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity."));
		 movies.add(new Movie("tt2395427", "Avengers: Age of Ultron","2015-05-01",141,"Joss Whedon","When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's Mightiest Heroes to stop the villainous Ultron from enacting his terrible plans."));
		 movies.add(new Movie("tt4046784", "Maze Runner: The Scorch Truals","2015-09-18",132,"Wes Ball","After having escaped the Maze, the Gladers now face a new set of challenges on the open roads of a desolate landscape filled with unimaginable obstacles."));
		 movies.add(new Movie("tt0478970", "Ant-Man","2015-07-17",117,"Peyton Reed","Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help his mentor, Dr. Hank Pym, plan and pull off a heist that will save the world."));		 
		 movies.add(new Movie("tt2310332","The Hobbit: The Battle of the Five Armies","2014-12-17",144,"Peter Jackson","Bilbo and Company are forced to engage in a war against an array of combatants and keep the Lonely Mountain from falling into the hands of a rising darkness."));
		 movies.add(new Movie("tt2908446","Insurgent", "2015-03-20", 119,"Robert Schwenkte", "Beatrice Prior must confront her inner demons and continue her fight against a powerful alliance which threatens to tear her society apart with the help from others on her side."));
		 movies.add(new Movie("tt2015381","Guardians of the Galaxy","2014-08-01",121,"James Gunn","A group of intergalactic criminals are forced to work together to stop a fanatical warrior from taking control of the universe"));
		 movies.add(new Movie("tt1981115","Thor: The Dark World","2013-11-08",112,"Alan Taylor","When Dr Jane Foster gets cursed with a powerful object, Thor must protect it before an army and its ruthless leader try to get their hands on it to take over the remains of Earth."));;
		 movies.add(new Movie("tt1877832","X-Men: Days of Future Past","2014-05-23",132,"Bryan Singer","The X-Men send Wolverine to the past in a desperate effort to change history and prevent an event that results in doom for both humans and mutants."));
		 movies.add(new Movie("tt0167260","The Lord of the Rings: The Return of the King","2003-12-17",201,"Peter Jackson","Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."));
		 arg0.getServletContext().setAttribute("notflix",notflix);
		 
		 Gebruiker geb1 = new Gebruiker("Peerhoofd", "", "Jopie", "nick", "test123") ;
		 Gebruiker geb2 = new Gebruiker("Test", "", "Johnny", "gebruiker", "Wachtwoord") ;
		 Gebruiker geb3 = new Gebruiker("Peerhoofd", "tussenvoegsel", "Jopie", "nick", "test123") ;
		 
		 notflix.addGebruiker(geb1);
		 notflix.addGebruiker(geb2);
		 notflix.addGebruiker(geb3);
		 
		 notflix.addRating(notflix.getMovie("tt1392190"), new Rating(5, geb1, notflix.getMovie("tt1392190")));
		 notflix.addRating(notflix.getMovie("tt0478970"), new Rating(5, geb2, notflix.getMovie("tt0478970")));
		 notflix.addRating(notflix.getMovie("tt1877832"), new Rating(5, geb3, notflix.getMovie("tt1877832")));
	}

}
