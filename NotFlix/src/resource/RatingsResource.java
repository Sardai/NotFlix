package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import models.Gebruiker;
import models.Movie;
import models.Notflix;
import models.Rating;

/**
 * 
 * @author Viradj
 *	REST class for requests for Ratings
 */

@Path("/ratings")
public class RatingsResource {
	
	@Context
	private ServletContext context;
	
	/**
	 * Creates new Rating
	 * @param sterren
	 * @param token
	 * @param movieId
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void addRating(@FormParam("sterren") int sterren, @HeaderParam("token") String token, @FormParam("imdbId") String movieId ){
		
		Notflix model = (Notflix) context.getAttribute("notflix");
		
		if(!model.hasRating(token, movieId)){
			Gebruiker gebruiker = model.getGebruiker(token);
			Movie movie = model.getMovie(movieId);		
			Rating newRating = new Rating(sterren, gebruiker, movie);
			
			model.addRating(movie, newRating);
		}		
	}
	
	/**
	 * 
	 * @param imdbId
	 * @param token
	 * @return Rating of specified movie by authorized user
	 */
	@GET
	@Path("{imdbId}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Rating getRating(@PathParam("imdbId") String imdbId, @HeaderParam("token") String token){
		Notflix model = (Notflix) context.getAttribute("notflix");
		
		return model.getRating(imdbId, token);
	}
	
	/**
	 * Deletes Rating
	 * @param movie
	 * @param token
	 */
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void deleteRating(@FormParam("imdbId") String movie, @HeaderParam("token") String token){
		Notflix model = (Notflix) context.getAttribute("notflix");

		if(model.getRating(movie, token) != null){
			model.deleteRating(movie, token);
		}
	}
	
	/**
	 * Updates an existing rating
	 * @param sterren
	 * @param token
	 * @param movieId
	 * @return the new updated rating
	 */
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Rating putRating(@FormParam("sterren") int sterren, @HeaderParam("token") String token, @FormParam("imdbId") String movieId){
		
		Notflix model = (Notflix) context.getAttribute("notflix");
		
		if(model.getRating(movieId, token) != null){
			model.updateRating(movieId, token, sterren);
		}
		
		return model.getRating(movieId, token);
	}

}
