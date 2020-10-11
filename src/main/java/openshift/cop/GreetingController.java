package openshift.cop;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@ApplicationScoped
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final Counter counter;

    public GreetingController() {
       counter = new Counter("greeting_counter");
    }

    @GET
    @Path("/v1/greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greeting(@QueryParam("name") @DefaultValue("World") final String name) {
        counter.increment();
        return new Greeting((int)counter.count(),
                            String.format(template, name));
    }

    // @GetMapping("/")
    // public RedirectView index() {
    //     return new RedirectView("/v1/greeting");
    // }

    @GET
    @Path("/v1/hostinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public HostInfo hostinfo() throws IOException {
      return new HostInfo();
    }
    
    @GET
    @Path("/v1/envinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public EnvInfo envinfo(@QueryParam(value="filter") @DefaultValue("*") final String filter) throws IOException {
      return new EnvInfo(filter);
    }
    
}
