package tdd.service;

import tdd.model.Game;

public class ScoreService {
    public int evaluate(Game game){
        return FamitsuAPI.evaluate(game.getGameName(), game.getProvider(), game.getPlatform());
    }
}

class FamitsuAPI{
    public static int evaluate(String game, String provider, String platform){
        return 30;
    }
}
