/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author chris
 *
 */
public class Notflix {
	
	private final List<Gebruiker> gebruikers;
	private final List<Movie> movies;
	private final Map<Gebruiker,String> tokens; 
	private final Map<Movie, Rating> ratings;
	
	public Notflix(){
		movies = new ArrayList<>();
		tokens = new HashMap<>();
		gebruikers = new ArrayList<>();
		ratings = new HashMap<>();
	}
	
	public List<Gebruiker> getGebruikers(){
		return gebruikers;
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
	
	public void addGebruiker(Gebruiker gebruiker){
		gebruikers.add(gebruiker);
	}
		
	public String createToken(String nickname,String password){
		 for (Gebruiker gebruiker : gebruikers) {
			if(gebruiker.getNickname().equals(nickname) && gebruiker.getWachtwoord().equals(password)){
				
				if(tokens.containsKey(gebruiker)){
					return tokens.get(gebruiker);
				}
				
				String token = generateToken();
				tokens.put(gebruiker, token);
				return token;
			}
		}
		 return null;
	}
	
	private String generateToken(){
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String token = "";
		int tokenLength = 48;
		Random random = new Random();
		for (int i = 0; i < tokenLength; i++) {
			token+= chars.charAt(random.nextInt(chars.length()));
		}
		
		return token;
	}
	
}
