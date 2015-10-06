package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/ratings")
public class RatingsResource {
	
	@Context
	private ServletContext context;
	
//	@POST
//	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
//	public void addRating(@FormParam("sterren") int sterren, @HeaderParam("token"), ){
//		
//	}

}
