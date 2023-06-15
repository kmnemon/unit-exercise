package tdd.resources;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import tdd.model.Game;
import tdd.repository.GameRepository;
import tdd.service.GameService;
import tdd.service.ScoreService;

import java.util.List;

import static tdd.resources.FormHelper.toGames;


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

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name){
        return repository.findByName(name).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response save(MultivaluedMap<String, String> form){
        toGames(form).forEach(repository::save);
        return Response.created(null).build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(){
        repository.deleteAll();
        return Response.created(null).build();
    }

    @GET
    @Path("/score/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameScore(@PathParam("name") String name){
        GameService gameService = new GameService(repository);
        return gameService.getScore(new ScoreService(), "mario").map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }



}
