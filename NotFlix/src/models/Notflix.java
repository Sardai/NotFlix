/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author chris,viradj
 * class wich contains users, movies and tokens
 */
public class Notflix {
	
	private final List<Gebruiker> gebruikers;
	private final List<Movie> movies;
	private final Map<Gebruiker,String> tokens; 
	private final Map<Movie, ArrayList<Rating>> ratings;
	
	/**
	 * Initiaze a new instance of notflix.
	 */
	public Notflix(){
		movies = new ArrayList<>();
		tokens = new HashMap<>();
		gebruikers = new ArrayList<>();
		ratings = new HashMap<>();
	}
	
	/**
	 * Get all users.
	 * @return the list with users.
	 */
	public List<Gebruiker> getGebruikers(){
		return gebruikers;
	}
	
	/**
	 * Get a user from token
	 * @param token token which is linked with user.
	 * @return
	 */
	public Gebruiker getGebruiker(String token){
		for(Map.Entry<Gebruiker, String> map : tokens.entrySet()){
			if(map.getValue().equals(token)){
				return map.getKey();
			}
		}
		return null;
	}
	
	/**
	 * Get all movies.
	 * @return the list with movies
	 */
	public List<Movie> getMovies(){
		return movies;
	}
	
	/**
	 * Get all movies with a specific title.
	 * @param title the titel of the movie
	 * @return all movies with the title of a part of the title
	 */
	public List<Movie> getMovies(String title){
		List<Movie> found = new ArrayList<>();
		for (Movie movie : movies) {
			if(movie.getTitel().toLowerCase().contains(title.toLowerCase())){
				found.add(movie);
			}
		}
		return found;
	}
	
	/**
	 * Get all movies from a certain director.
	 * @param director the director of the movies
	 * @return the list of all movies with a certain director
	 */
	public List<Movie> getMoviesFromDirector(String director){
		List<Movie> found = new ArrayList<>();
		for (Movie movie : movies) {
			if(movie.getRegisseur().toLowerCase().contains(director.toLowerCase())){
				found.add(movie);
			}
		}
		return found;
	}
	
	/**
	 * Get movie with imdb id.
	 * @param imdbId the id
	 * @return the movie
	 */
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
	
	/**
	 * Removes rating from movie.
	 * @param imdbId the id of the movie
	 * @param token the token of the user
	 */
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
	
	/**
	 * Add user to user list.
	 * @param gebruiker the user
	 */
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
				calculateAverageRating(movie, ratingsList);
				return;
			}
		}
		
		ArrayList<Rating> newRatingList = new ArrayList<Rating>();
		newRatingList.add(rating);		
		ratings.put(movie, newRatingList);
		calculateAverageRating(movie, newRatingList);
	}
	
	public void updateRating(String imdbId, String token, int sterren){
		
		Gebruiker gebruiker = getGebruiker(token);
		
		for(Map.Entry<Movie, ArrayList<Rating>> map : ratings.entrySet()){
			if(map.getKey().getImdbId().equals(imdbId) && hasGebruiker(map.getValue(), gebruiker)){
				ArrayList<Rating> ratingsList = map.getValue();
				ratingsList.remove(getRatingFromList(ratingsList, gebruiker));
				ratingsList.add(new Rating(sterren, gebruiker, map.getKey()));
				map.setValue(ratingsList);
				calculateAverageRating(map.getKey(),ratingsList);
				return;
			}
		}		
	}
	
	/**
	 * Checks if a token is valid.
	 * @param token the token
	 * @return if the token is valid
	 */
	public boolean hasToken(String token){
		for(Map.Entry<Gebruiker,String> map : tokens.entrySet() ){
			if(map.getValue().equals(token)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Create a token for user.
	 * @param nickname the nickname of the user
	 * @param password the password of the user
	 * @return the token
	 */
	public String createToken(String nickname,String password){
		 for (Gebruiker gebruiker : gebruikers) {
			if(gebruiker.getNickname().equals(nickname) && gebruiker.getWachtwoord().equals(password)){
				
				if(tokens.containsKey(gebruiker)){
					return tokens.get(gebruiker);
				}
				
				String token = UUID.randomUUID().toString();
				tokens.put(gebruiker, token);
				return token;
			}
		}
		 return null;
	}
	
	/**
	 * Calculates average rating and updates movie with the value/ 
	 * @param movie the movie
	 * @param ratings the ratings which belongs to the movie.
	 */
	private void calculateAverageRating(Movie movie,List<Rating> ratings){
		double sum = 0;
		for (Rating rating : ratings) {
			sum += rating.getSterren();
		}
		double average = sum/ratings.size();
		average=Math.floor(average*10) / 10;
		movie.setAverageRating(average);
	}
	
	 
	
}
