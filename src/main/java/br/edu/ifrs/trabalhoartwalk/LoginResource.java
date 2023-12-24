package br.edu.ifrs.trabalhoartwalk;

//import br.edu.ifrs.trabalhoartwalk.resources.AuthService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@HeaderParam("username") String username, @HeaderParam("password") String password) {
        //AuthService authService = new AuthService();
        //String token = authService.login(username, password);
        String token = "tokenfake";

        if (token != null) {
            // Se a autenticação for bem-sucedida, retorne o token JWT
            return Response.ok().header("Authorization", "Bearer " + token).build();
        } else {
            // Se a autenticação falhar, retorne uma resposta não autorizada (401)
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
