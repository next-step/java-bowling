package bowling.domain.result;

import java.util.List;

public class GameResults {

    private final List<GameResult> values;

    public GameResults(List<GameResult> values) {
        this.values = values;
    }

    public List<GameResult> getValues() {
        return values;
    }

}
