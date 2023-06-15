package tdd.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.model.Game;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRepositoryTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private GameRepository repository;
    Game mario;

    @BeforeEach
    public void before(){
        factory = Persistence.createEntityManagerFactory("game");
        manager = factory.createEntityManager();
        repository = new GameRepository(manager);

        mario = repository.save(new Game("mario", "nintendo", "switch"));
        repository.save(new Game("zelda", "nintendo", "switch"));
    }

    @AfterEach
    public void after(){
        manager.clear();
        manager.close();
        factory.close();
    }

    @Test
    public void shouldGenereateIdForSavedEntity(){
    }

    @Test
    public void shouldBeAbleToLoadSavedGameById(){


    }

    //TODO: findByName
    //TODO: find all
    //TODO: save
    //TODO: delete




}
