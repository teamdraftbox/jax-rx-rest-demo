package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/ping")
public class helloWorld {

	@GET
	public String greet() {
		return "pong";
	}
	
	@GET
	@Produces("application/json")
	public String HelloeXml() {
		return "";
	}
	
    @GET
    @Produces("application/xml")
    public int greetXml() { return 6;}
    
    
}
