package tdd.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import tdd.model.Game;

import java.util.List;
import java.util.Optional;

public class GameRepository {
    private EntityManager manager;

    public GameRepository(EntityManager manager) {
        this.manager = manager;
    }

    public Game save(Game game){
        manager.getTransaction().begin();
        manager.persist(game);
        manager.getTransaction().commit();
        return game;
    }

    public void deleteAll(){
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Game").executeUpdate();
        manager.getTransaction().commit();
    }

    public Optional<Game> findById(long id){
        Game value = manager.find(Game.class, id);
        return Optional.ofNullable(value);
    }

    public Optional<Game> findByName(String gameName){
        TypedQuery<Game> query = manager.createQuery("SELECT g from Game g where g.gameName = :gameName", Game.class);
        return query.setParameter("gameName", gameName).getResultList().stream().findFirst();
    }

    public List<Game> all(){
        TypedQuery<Game> query = manager.createQuery("SELECT g from Game g", Game.class);
        return query.getResultList();
    }
}

