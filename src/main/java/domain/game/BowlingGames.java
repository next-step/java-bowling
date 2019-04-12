package domain.game;

import java.util.ArrayList;
import java.util.List;

public class BowlingGames {

    private final List<BowlingGame> games = new ArrayList<>();

    public List<BowlingGame> getGames() {
        return games;
    }

    public BowlingGame get(int i) {
        return games.get(i);
    }

    public void add(BowlingGame game) {
        games.add(game);
    }

    public int size() {
        return games.size();
    }

    public boolean isFinished() {
        return games.stream()
                .filter(BowlingGame::isContinuable)
                .count() <= 0;
    }
}