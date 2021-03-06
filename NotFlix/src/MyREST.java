import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class MyREST extends ResourceConfig {
	public MyREST() {
		packages("resource");
		register(CORSResponseFilter.class);
		register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
	}
}