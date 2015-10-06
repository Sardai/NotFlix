/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chris
 *
 */
public class Notflix {

	private final List<Movie> movies;
	private final List<String> tokens; 
	
	public Notflix(){
		movies = new ArrayList<>();
		tokens = new ArrayList<>();
	}
	
	public List<Movie> getMovies(){
		return movies;
	}
	
	public List<Movie> getMovies(String title){
		List<Movie> found = new ArrayList<>();
		for (Movie movie : movies) {
			if(movie.getTitel().toLowerCase().contains(title.toLowerCase())){
				found.add(movie);
			}
		}
		return found;
	}
	
	public List<Movie> getMoviesFromDirector(String director){
		List<Movie> found = new ArrayList<>();
		for (Movie movie : movies) {
			if(movie.getRegisseur().toLowerCase().contains(director.toLowerCase())){
				found.add(movie);
			}
		}
		return found;
	}
	
	public Movie getMovie(String imdbId){
		if(!imdbId.startsWith("tt")){
			imdbId = "tt"+imdbId;
		}
		for(Movie movie: movies){
			if(movie.getImdbId().equals(imdbId)){
				return movie;
			}
		}
		return null;
	}	
	
	public void createToken(){
		
	}
	
}
