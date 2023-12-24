/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package br.edu.ifrs.trabalhoartwalk;

import br.edu.ifrs.modelo.Produtos;
import com.google.gson.Gson;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("produtos")
@RequestScoped
public class ProdutosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CamisasResource
     */
    public ProdutosResource() {
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
        
        Produtos produtos[];
        
        try {
            Produtos c = new Produtos();
            produtos = c.selecionar();
        } catch (Exception e) {
            produtos = new Produtos[0];
        }
        
        return g.toJson(produtos);
    }

    /**
     * PUT method for updating or creating an instance of CamisasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
     /**
     * Método DELETE para excluir um produto pelo ID.
     * @param id ID do produto a ser excluído.
     * @return Resposta de sucesso ou falha.
     */
    @DELETE
    @Path("{id}")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response deleteProduto(@PathParam("id") String id) {
        try {
            Produtos produto = new Produtos();
            produto.excluir(id);
            return Response.ok().build(); 
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir o produto").build();
        }
    }
}
