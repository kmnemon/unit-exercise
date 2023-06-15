package tdd.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.model.Game;
import tdd.repository.GameRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {
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

    @Test
    public void testGetScoreByState(){

    }

    @Test
    public void testGetScoreByBehaviour(){

    }
}

