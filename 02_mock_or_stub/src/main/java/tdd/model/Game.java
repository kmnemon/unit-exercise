package tdd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Game")
public class Game {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "provider")
    private String provider;

    @Column(name = "platform")
    private String platform;

    public Game() {
    }

    public Game(String gameName, String provider, String platform) {
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", provider='" + provider + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
