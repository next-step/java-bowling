package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResults {
    private List<GameResult> results;

    private GameResults() {
        this.results = new ArrayList<>();
    }

    public static GameResults create() {
        return new GameResults();
    }

    public boolean record(GameResult result) {
        return results.add(result);
    }

    public List<GameResult> getHistory() {
        return Collections.unmodifiableList(results);
    }
}
