package tdd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import tdd.repository.GameRepository;
import tdd.repository.GameRepositoryFakeDB;
import tdd.resources.GameResource;

public class ApplicationFakeDB extends ResourceConfig {
    public ApplicationFakeDB(){
        this.packages("tdd");
        this.register(GameResource.class);

        //TODO: fake repository need inject
        this.register(new AbstractBinder() {
            @Override
            protected void configure() {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("game");
                EntityManager entityManager = factory.createEntityManager();
                GameRepository gameRepository = new GameRepository(entityManager);
                bind(gameRepository).to(GameRepository.class);
            }
        });
    }
}
