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
	private final Map<Movie, ArrayList<Rating>> ratings;
	
	public Notflix(){
		movies = new ArrayList<>();
		tokens = new HashMap<>();
		gebruikers = new ArrayList<>();
		ratings = new HashMap<>();
	}
	
	public List<Gebruiker> getGebruikers(){
		return gebruikers;
	}
	
	public Gebruiker getGebruiker(String token){
		for(Map.Entry<Gebruiker, String> map : tokens.entrySet()){
			if(map.getValue().equals(token)){
				return map.getKey();
			}
		}
		return null;
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
	
	public Rating getRating(String imdbId, String token){
		Gebruiker gebruiker = getGebruiker(token);
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet()){
			if(map.getKey().getImdbId().equals(imdbId) && hasGebruiker(map.getValue(), gebruiker)){
				return getRatingFromList(map.getValue(), gebruiker);
			}
		}
		
		return null;
	}
	
	public Rating getRatingFromList(ArrayList<Rating> ratings, Gebruiker gebruiker){
		
		for(Rating r : ratings){
			if(r.getGebruiker() == gebruiker){
				return r;
			}
		}
		
		return null;
	}
	
	public boolean hasGebruiker(ArrayList<Rating> ratings, Gebruiker gebruiker){
		
		boolean result = false;
		
		for(Rating r : ratings){
			if(r.getGebruiker() == gebruiker){
				result = true;
			}
		}
		
		return result;
	}
	
	public void deleteRating(String imdbId, String token){
		Gebruiker gebruiker = getGebruiker(token);
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet()){
			if(map.getKey().getImdbId().equals(imdbId) && hasGebruiker(map.getValue(), gebruiker)){
				ArrayList<Rating> ratingsList = map.getValue();
				ratingsList.remove(getRatingFromList(ratingsList, gebruiker));
				map.setValue(ratingsList);
			}
		}
	}
	
	public void addGebruiker(Gebruiker gebruiker){
		gebruikers.add(gebruiker);
	}
	
	public boolean hasRating(String token, String imdbId){
		
		boolean result = false;
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet()){
			if(map.getKey().getImdbId().equals(imdbId) && hasGebruiker(map.getValue(), getGebruiker(token)) ){
				result = true;
			}
		}
		
		return result;
	}
	
	public void addRating(Movie movie, Rating rating){
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet() ){
			if(map.getKey() == movie){
				ArrayList<Rating> ratingsList = map.getValue();
				ratingsList.add(rating);
				map.setValue(ratingsList);
				return;
			}
		}
		
		ArrayList<Rating> newRatingList = new ArrayList<Rating>();
		newRatingList.add(rating);		
		ratings.put(movie, newRatingList);
	}
	
	public void updateRating(String imdbId, String token, int sterren){
		
		Gebruiker gebruiker = getGebruiker(token);
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet()){
			if(map.getKey().getImdbId().equals(imdbId) && hasGebruiker(map.getValue(), gebruiker)){
				ArrayList<Rating> ratingsList = map.getValue();
				ratingsList.remove(getRatingFromList(ratingsList, gebruiker));
				ratingsList.add(new Rating(sterren, gebruiker, map.getKey()));
				map.setValue(ratingsList);
			}
		}		
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
