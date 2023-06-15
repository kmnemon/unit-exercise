package tdd.service;

import exception.DataNotFoundException;
import tdd.model.Game;
import tdd.repository.GameRepository;

import java.util.Optional;

public class GameService {
    private GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Optional<Integer> getScore(ScoreService scoreService, String gameName){
        try {
            Game game = repository.findByName(gameName).orElseThrow(DataNotFoundException::new);
            return Optional.of(scoreService.evaluate(game));
        } catch (DataNotFoundException e){
            return Optional.empty();
        }
    }
}
