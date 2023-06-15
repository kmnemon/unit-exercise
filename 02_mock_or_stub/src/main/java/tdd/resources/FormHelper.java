package tdd.resources;

import jakarta.ws.rs.core.MultivaluedMap;
import tdd.model.Game;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FormHelper {
    public static Stream<Game> toGames(MultivaluedMap<String, String> form) {
        List<String> gameNames = form.get("games[game_name]");
        List<String> providers = form.get("games[provider]");
        List<String> platforms = form.get("games[platform]");

        return IntStream.range(0, gameNames.size()).mapToObj(it -> new Game(gameNames.get(it),
                providers.get(it), platforms.get(it)));
    }

}
