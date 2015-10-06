package resource;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import models.GebruikersModel;
import models.Notflix;

@Path("/gebruikers")
public class GebruikersResource {
	
	@Context
	private ServletContext context;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<GebruikersModel> getGebruikers(){
		Notflix model = (Notflix) context.getAttribute("notflix");
		return model.getGebruikers();
	}
	
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public GebruikersModel getGebruiker(@PathParam("nickname") String nickname){
		Notflix model = (Notflix) context.getAttribute("notflix");
		for(GebruikersModel gebruiker : model.getGebruikers()){
			if(gebruiker.getNickname().equals(nickname)){
				return gebruiker;
			}
		}
		return new GebruikersModel();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void addGebruiker(@FormParam("achternaam") String achternaam, @FormParam("tussenvoegsel") String tussenvoegsel,
			@FormParam("voornaam") String voornaam, @FormParam("nickname") String nickname, @FormParam("wachtwoord") String wachtwoord){
		
		GebruikersModel newGebruiker = new GebruikersModel(achternaam, tussenvoegsel, voornaam, nickname, wachtwoord);
		Notflix model = (Notflix) context.getAttribute("notflix");
		
		model.addGebruiker(newGebruiker);
		
	}

}
