/**
 * 
 */
package models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author chris
 *
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
	
	private static int lastId;

	public Movie(){
		
	}
	
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
	public int getId() {
		return id;
	}

	

	public String getImdbId() {
		return imdbId;
	}

	public String getTitel() {
		return titel;
	}

	public String getDatum() {
		return datum;
	}

	public int getLengte() {
		return lengte;
	}

	public String getRegisseur() {
		return regisseur;
	}

	public String getBeschrijving() {
		return beschrijving;
	}
 
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public void setLengte(int lengte) {
		this.lengte = lengte;
	}

	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	
	
	
	
}
