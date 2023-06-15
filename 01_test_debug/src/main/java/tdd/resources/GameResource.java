package tdd.resources;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import tdd.model.Game;
import tdd.model.GameRepository;

import java.util.List;
import java.util.stream.IntStream;

@Path("/games")
public class GameResource {
    private GameRepository repository;

    @Inject
    public GameResource(GameRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> all(){
        return repository.all();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        return repository.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response save(MultivaluedMap<String, String> form){
        List<String> gameNames = form.get("game[game_name]");
        List<String> providers = form.get("game[provider]");
        List<String> platforms = form.get("game[platform]");

        IntStream.range(0, gameNames.size()).mapToObj(it -> new Game(gameNames.get(it),
                providers.get(it), platforms.get(it)))
                .forEach(repository::save);

        return Response.created(null).build();
    }


}
