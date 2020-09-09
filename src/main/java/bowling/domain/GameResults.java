package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResults extends ArrayList {
    private List<GameResult> results;

    private GameResults() {
        this.results = new ArrayList<>();
    }

    public static GameResults newGameResults() {
        return new GameResults();
    }

    public boolean add(GameResult result) {
        return results.add(result);
    }

    @Override
    public int size() {
        return results.size();
    }

    @Override
    public GameResult get(int index) {
        return results.get(index);
    }
}
