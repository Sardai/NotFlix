package resource;

import java.util.List;

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
import javax.ws.rs.core.Response;

import models.Gebruiker;
import models.Notflix;

/**
 * 
 * @author Viradj
 *	REST class for requests for users
 */

@Path("/gebruikers")
public class GebruikersResource {
	
	@Context
	private ServletContext context;
	
	/**
	 * 
	 * @return all users
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Gebruiker> getGebruikers(){
		Notflix model = (Notflix) context.getAttribute("notflix");
		return model.getGebruikers();
	}
	
	/**
	 * 
	 * @param nickname
	 * @return user specified by nickname
	 */
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getGebruiker(@PathParam("nickname") String nickname){
		Notflix model = (Notflix) context.getAttribute("notflix");
		for(Gebruiker gebruiker : model.getGebruikers()){
			if(gebruiker.getNickname().equals(nickname)){
				return Response.ok(gebruiker).build();
			}
		}
		return Response.status(401).build();
	}
	
	/**
	 * 
	 * @param nickname
	 * @param password
	 * @return Token for requested user
	 */
	@POST
	@Path("/token")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getToken(Gebruiker gebruiker){
		Notflix model = (Notflix) context.getAttribute("notflix");
		String token = model.createToken(gebruiker.getNickname(), gebruiker.getWachtwoord());
				
		if(token == null){
			return Response.status(401).build();
		}
		
		return Response.ok(token).build();
		
	}
	
	/**
	 * Creates new User
	 * @param achternaam
	 * @param tussenvoegsel
	 * @param voornaam
	 * @param nickname
	 * @param wachtwoord
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Response addGebruiker(@FormParam("achternaam") String achternaam, @FormParam("tussenvoegsel") String tussenvoegsel,
			@FormParam("voornaam") String voornaam, @FormParam("nickname") String nickname, @FormParam("wachtwoord") String wachtwoord){
		
		Notflix model = (Notflix) context.getAttribute("notflix");
		
		if(!model.userExists(nickname)){
			Gebruiker newGebruiker = new Gebruiker(achternaam, tussenvoegsel, voornaam, nickname, wachtwoord);
			model.addGebruiker(newGebruiker);
			return Response.ok(200).build();
		}
		
		return Response.status(401).build();
	}

}
