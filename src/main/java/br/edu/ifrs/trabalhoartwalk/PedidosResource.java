package br.edu.ifrs.trabalhoartwalk;

import br.edu.ifrs.modelo.Pedidos;
import com.google.gson.Gson;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.enterprise.context.RequestScoped;

@Path("pedidos")
@RequestScoped
public class PedidosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CamisasResource
     */
    public PedidosResource() {
    }

    /**
     * Retrieves representation of an instance of br.edu.ifrs.aula0612.CamisasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        /*throw new UnsupportedOperationException();*/
        Gson g = new Gson();
        
        Pedidos pedidos[];
        
        try {
            Pedidos c = new Pedidos();
            pedidos = c.selecionar();
        } catch (Exception e) {
            pedidos = new Pedidos[0];
        }
        
        return g.toJson(pedidos);
    }

    /**
     * PUT method for updating or creating an instance of CamisasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}