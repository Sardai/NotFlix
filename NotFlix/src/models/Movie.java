/**
 * 
 */
package models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author chris
 *	The movie object with all information of a movie.
 */
@XmlRootElement
public class Movie {
	
	private int id;
	private String imdbId;
	private String titel;
	private String datum;
	private int lengte;
	private String regisseur;
	private String beschrijving;
	private double averageRating;
	private static int lastId;

	public Movie(){
		
	}
	/**
	 * Create a new movie with all values and a new id. 
	 * @param imdbId the id of the movie
	 * @param titel the title of the movie
	 * @param datum the date of the movie
	 * @param lengte the length of the movie
	 * @param regisseur the director of the movie
	 * @param beschrijving the description of the movie
	 */
	public Movie(String imdbId, String titel, String datum, int lengte, String regisseur, String beschrijving) {
		lastId++;
		this.id = lastId;
		this.imdbId = imdbId;
		this.titel = titel;
		this.datum = datum;
		this.lengte = lengte;
		this.regisseur = regisseur;
		this.beschrijving = beschrijving;
	}

	@JsonIgnore
	@XmlTransient
	/**
	 * @return the id of the movie
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the imdbId
	 */
	public String getImdbId() {
		return imdbId;
	}
	/**
	 * @param imdbId the imdbId to set
	 */
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	/**
	 * @return the titel
	 */
	public String getTitel() {
		return titel;
	}
	/**
	 * @param titel the titel to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	/**
	 * @return the datum
	 */
	public String getDatum() {
		return datum;
	}
	/**
	 * @param datum the datum to set
	 */
	public void setDatum(String datum) {
		this.datum = datum;
	}
	/**
	 * @return the lengte
	 */
	public int getLengte() {
		return lengte;
	}
	/**
	 * @param lengte the lengte to set
	 */
	public void setLengte(int lengte) {
		this.lengte = lengte;
	}
	/**
	 * @return the regisseur
	 */
	public String getRegisseur() {
		return regisseur;
	}
	/**
	 * @param regisseur the regisseur to set
	 */
	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}
	/**
	 * @return the beschrijving
	 */
	public String getBeschrijving() {
		return beschrijving;
	}
	/**
	 * @param beschrijving the beschrijving to set
	 */
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the averageRating
	 */
	public double getAverageRating() {
		return averageRating;
	}
	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	/**
	 * @return the lastId
	 */
	public static int getLastId() {
		return lastId;
	}
	/**
	 * @param lastId the lastId to set
	 */
	public static void setLastId(int lastId) {
		Movie.lastId = lastId;
	}

	
	
 
	
	
	
}
