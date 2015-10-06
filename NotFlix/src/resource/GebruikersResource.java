package resource;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Gebruiker;
import models.Notflix;

@Path("/gebruikers")
public class GebruikersResource {
	
	@Context
	private ServletContext context;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Gebruiker> getGebruikers(){
		Notflix model = (Notflix) context.getAttribute("notflix");
		return model.getGebruikers();
	}
	
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Gebruiker getGebruiker(@PathParam("nickname") String nickname){
		Notflix model = (Notflix) context.getAttribute("notflix");
		for(Gebruiker gebruiker : model.getGebruikers()){
			if(gebruiker.getNickname().equals(nickname)){
				return gebruiker;
			}
		}
		return new Gebruiker();
	}
	
	@POST
	@Path("/token")
	public Response getToken(@FormParam(value = "") String nickname,@FormParam(value = "") String password){
		Notflix model = (Notflix) context.getAttribute("notflix");
		String token = model.createToken(nickname, password);
				
		if(token == null){
			return Response.status(401).build();
		}
		
		return Response.ok(token).build();
		
	}
	

}
