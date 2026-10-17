package webservices;

// Import the necessary JAX-RS (Java API for RESTful Web Services) annotations and classes
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import java.util.List;
import javax.ws.rs.POST;// Annotation to indicate that a method responds to an HTTP GET request
import javax.ws.rs.Path;              // Annotation to define the URL path of a resource
import javax.ws.rs.PathParam;
import metiers.UniteEnseignementBusiness;
import javax.ws.rs.Consumes;
import entities.UniteEnseignement;// Annotation to extract dynamic values from the URL
import javax.ws.rs.Produces;          // Annotation to define the type of content returned (text, JSON, XML…)
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;    // Provides constants for standard media types (e.g., TEXT_PLAIN, APPLICATION_JSON…)
import javax.ws.rs.core.Response;     // Class used to build a custom HTTP response
// The resource will be accessible at the base URL followed by /hello
@Path("/hello")
public class HelloRessources {
    private UniteEnseignementBusiness helper =
            new UniteEnseignementBusiness();
    // This method handles GET requests sent to /hello/hi
    @GET
    @Path("/hi")
    @Produces(MediaType.TEXT_PLAIN)   // The response will be returned as plain text
    public Response sayHello() {
        // Build an HTTP 200 (OK) response with the message "Hello World!"
        return Response
                .status(200)                  // HTTP status code 200 = success
                .entity("Hello World!")       // The body of the response
                .build();                     // Builds the final Response object
    }

    // This method handles GET requests sent to /hello/{name}
    // Example: /hello/Ahmed -> will return "Hello Ahmed!"
    @GET
    @Path("/{name}")                          // {name} is a dynamic variable in the URL
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloT(@PathParam(value = "name") String name) {
        // Build a personalized response message using the provided name
        return Response
                .status(200)
                .entity("Hello " + name + "!") // Insert the name from the URL into the response
                .build();
    }
//ajoute//
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("Success").build();
        } else {
            return Response.status(400).entity("Erreur").build();
        }
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UniteEnseignement> getAllUE() {
        return helper.getListeUE();
    }
    //remove//
    @DELETE
    @Path("/delete/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {

        if (helper.deleteUniteEnseignement(code)) {
            return Response.ok("UE deleted successfully").build();
        } else {
            return Response.status(404)
                    .entity("UE not found")
                    .build();
        }
    }
    //mise a jour //
    @PUT
    @Path("/update/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(@PathParam("code") int code,
                             UniteEnseignement ue) {

        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.ok("UE updated successfully").build();
        } else {
            return Response.status(404)
                    .entity("UE not found")
                    .build();
        }
    }

}
