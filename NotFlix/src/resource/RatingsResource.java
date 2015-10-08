package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import models.Gebruiker;
import models.Movie;
import models.Notflix;
import models.Rating;

@Path("/ratings")
public class RatingsResource {
	
	@Context
	private ServletContext context;
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void addRating(@FormParam("sterren") int sterren, @HeaderParam("token") String token, @FormParam("imdbId") String movieId ){
		
		Notflix model = (Notflix) context.getAttribute("notflix");
		Gebruiker gebruiker = model.getGebruiker(token);
		Movie movie = model.getMovie(movieId);
		
		Rating newRating = new Rating(sterren, gebruiker, movie);
		
		model.addRating(movie, newRating);
	}

}
