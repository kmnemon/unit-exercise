package tdd;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import tdd.model.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new tdd.Application();
    }

    @Test
    public void testShouldFectchAllGamesFromApi(){
        Game[] games = target("games").request().get(Game[].class);

        assertEquals(3, games.length);
        assertEquals("mario", games[0].getGameName());
        assertEquals("nintendo", games[0].getProvider());
        assertEquals("switch", games[0].getPlatform());
        assertEquals(1, games[0].getId());
    }

    @Test
    public void testShouldBeAbleFetchGamesById(){
        Game game = target("games/1").request().get(Game.class);

        assertEquals("mario", game.getGameName());
        assertEquals("nintendo", game.getProvider());
        assertEquals("switch", game.getPlatform());
        assertEquals(1, game.getId());
    }

    @Test
    public void testShouldReturn404IfNoGameFound(){
        Response response = target("games/4").request().get(Response.class);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

    @Test
    public void testShouldCreateGamesViaApi(){
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
}
