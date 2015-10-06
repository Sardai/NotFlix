/**
 * 
 */
package models;

import java.util.ArrayList;

/**
 * @author chris
 *
 */
public class Notflix {
	
	private final ArrayList<GebruikersModel> gebruikers = new ArrayList<GebruikersModel>();
	
	public ArrayList<GebruikersModel> getGebruikers(){
		return gebruikers;
	}
	
	public void addGebruiker(GebruikersModel gebruiker){
		gebruikers.add(gebruiker);
	}

}
