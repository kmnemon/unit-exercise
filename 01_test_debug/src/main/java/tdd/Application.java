package tdd;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import tdd.model.Game;
import tdd.model.GameRepository;
import tdd.resources.GameResource;


public class Application extends ResourceConfig {
    public Application(){
        this.packages("tdd");
        this.register(GameResource.class);

        this.register(new AbstractBinder() {
            @Override
            protected void configure() {
                Game a = new Game(1, "mario","nintendo","switch");
                Game b = new Game(2, "starcraft", "blizzard", "win");
                Game c = new Game(3, "Honor of kings", "tencent", "ios");
                bind(new GameRepository(a, b, c)).to(GameRepository.class);
            }
        });
    }
}
