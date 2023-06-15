package tdd.model;


public class Game {
    private long id;
    private String gameName;
    private String provider;
    private String platform;

    public Game() {
    }

    public Game(String gameName, String provider, String platform) {
        this.gameName = gameName;
        this.provider = provider;
        this.platform = platform;
    }

    public Game(long id, String gameName, String provider, String platform) {
        this.id = id;
        this.gameName = gameName;
        this.provider = provider;
        this.platform = platform;
    }

    public long getId() {
        return id;
    }

    public String getGameName() {
        return gameName;
    }

    public String getProvider() {
        return provider;
    }

    public String getPlatform() {
        return platform;
    }

}
