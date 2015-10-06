/**
 * 
 */
package resource;



import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import models.Movie;
import models.Notflix;

/**
 * @author chris
 *
 */

@Path("/movies")
public class MoviesResource {
	
	@Context
	private ServletContext context; 
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Movie> getMovies(){
		Notflix model = (Notflix)context.getAttribute("notflix");
		return model.getMovies();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Movie getMovie(@PathParam("id") String id){
		Notflix model = (Notflix)context.getAttribute("notflix");
		return model.getMovie(id);
	}
	
	@GET
	@Path("/title/{title}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})	
	public List<Movie> getMoviesWithTitle(@PathParam("title") String title){
		Notflix model = (Notflix)context.getAttribute("notflix");
		return model.getMovies(title);
	}
	
	@GET
	@Path("/regisseur/{regisseur}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})	
	public List<Movie> getMoviesFromDirector(@PathParam("regisseur") String director){
		Notflix model = (Notflix)context.getAttribute("notflix");
		return model.getMoviesFromDirector(director);
	}
	
}