package tdd.resources;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.model.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new tdd.Application();
    }

    @BeforeEach
    public void before() {
        Form form = new Form();
        form.param("games[game_name]", "mario")
                .param("games[provider]", "nintendo")
                .param("games[platform]", "switch");
        form.param("games[game_name]", "starcraft")
                .param("games[provider]", "blizzard")
                .param("games[platform]", "win");
        form.param("games[game_name]", "Honor of kings")
                .param("games[provider]", "tencent")
                .param("games[platform]", "ios");

        target("games").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

    }

    @AfterEach
    public void after() {
        target("games/delete").request().delete();
    }




    @Test
    public void testShouldBeAbleFetchGamesById() {
        Game game = target("games/1").request().get(Game.class);

    }

    @Test
    public void testShouldReturn404IfNoGameFound() {
        Response response = target("games/4").request().get(Response.class);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

    @Test
    public void testShouldCreateGamesViaApi() {
        Game[] before = target("games").request().get(Game[].class);
        assertEquals(3, before.length);

        Form form = new Form();
        form.param("games[game_name]", "sonic")
                .param("games[provider]", "sega")
                .param("games[platform]", "sega16");
        form.param("games[game_name]", "gta5")
                .param("games[provider]", "star")
                .param("games[platform]", "xboxone");

        Response response = target("games").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        Game[] after = target("games").request().get(Game[].class);
        assertEquals(5, after.length);

        assertEquals("sonic", after[3].getGameName());
        assertEquals("sega", after[3].getProvider());
        assertEquals("sega16", after[3].getPlatform());
        assertNotEquals(0L, after[3].getId());

        assertEquals("gta5", after[4].getGameName());
        assertEquals("star", after[4].getProvider());
        assertEquals("xboxone", after[4].getPlatform());
        assertNotEquals(0L, after[4].getId());
    }

    //TODO:fetch by name
    //TODO:fetch all from api
    //TODO:delete
    //TODO:get score

}
