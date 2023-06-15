package tdd.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class GameRepository {
    private List<Game> games;

    public GameRepository(Game... games) {
        this.games = asList(games);
    }

    public void save(Game game) {
        games.add(game);
    }

    public Optional<Game> findById(long id) {
        return games.stream().filter(it->it.getId() == id).findFirst();
    }

    public List<Game> all() {
        return Collections.unmodifiableList(games);
    }


}
