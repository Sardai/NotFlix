package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {
	
	private int sterren;
	private Gebruiker gebruiker;
	private Movie movie;
	
	public Rating(){
		
	}
	
	public Rating(int sterren, Gebruiker gebruiker, Movie movie){
		this.sterren = sterren;
		this.gebruiker = gebruiker;
		this.movie = movie;
	}
	
	public int getSterren(){
		return sterren;
	}
	
	public void setSterren(int sterren){
		this.sterren = sterren;
	}
	
	public Gebruiker getGebruiker(){
		return gebruiker;
	}
	
	public void setGebruiker(Gebruiker gebruiker){
		this.gebruiker = gebruiker;
	}
	
	public Movie getMovie(){
		return movie;
	}
	
	public void setMovie(Movie movie){
		this.movie = movie;
	}

}
