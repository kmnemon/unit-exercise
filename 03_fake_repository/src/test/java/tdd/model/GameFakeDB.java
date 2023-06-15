package tdd.model;


public class GameFakeDB extends Game{
    public GameFakeDB(long id, String gameName, String provider, String platform) {
        super(gameName, provider, platform);
        this.id = id;
    }
}
