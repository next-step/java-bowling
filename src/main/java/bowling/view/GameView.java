package bowling.view;

import bowling.domain.Game;

import java.util.List;

public class GameView {

    private final Game game;
    private final String name;

    public GameView(String name) {
        this.name = name;
        this.game = Game.start();
    }

    public String getName() {
        return name;
    }

    public boolean isFinish() {
        return game.isFinish();
    }

    public int getPlayNumber() {
        return game.getPlayNumber();
    }

    public List<List<String>> hit(int hitCount) {
        return game.hit(hitCount);
    }

    public List<Integer> getSumScores() {
        return game.getSumScores();
    }
}
