package tdd.repository;

import tdd.model.Game;
import tdd.model.GameFakeDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class GameRepositoryFakeDB{
    private List<Game> games;

    public GameRepositoryFakeDB(Game... games) {
        this.games = new ArrayList<>(asList(games));
    }

    public Game save(Game game) {
        if (game.getId() == 0) {
            game = new GameFakeDB(games.size() == 0 ? 1L : games.get(games.size() - 1).getId() + 1, game.getGameName(), game.getProvider(), game.getPlatform());
        }
        games.add(game);
        return game;
    }

    public void deleteAll(){
        games.clear();
    }

    public Optional<Game> findById(long id) {
        return games.stream().filter(it->it.getId() == id).findFirst();
    }

    public Optional<Game> findByName(String gameName) {
        return games.stream().filter(it->it.getGameName().equals(gameName)).findFirst();
    }

    public List<Game> all() {
        return Collections.unmodifiableList(games);
    }


}
