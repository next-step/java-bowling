package bowling.domain.result;

import java.util.Collections;
import java.util.List;

public class GameResults {
    private final List<GameResult> results;

    public GameResults(List<GameResult> results) {
        this.results = Collections.unmodifiableList(results);
    }
}
