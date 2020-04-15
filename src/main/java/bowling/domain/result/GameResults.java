package bowling.domain.result;

import bowling.domain.PlayerName;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GameResults {
    private static final int INDEX_FIRST = 0;
    private static final int INDEX_SECOND = 1;

    private final List<GameResult> results;

    public GameResults(List<GameResult> results) {
        validateDuplicationPlayerName(results);
        this.results = Collections.unmodifiableList(results);
    }

    public static GameResults of(List<GameResult> results){
        return new GameResults(results);
    }

    private void validateDuplicationPlayerName(List<GameResult> results) {
        Set<String> names = results.stream()
                .map(GameResult::getPlayerName)
                .map(PlayerName::getName)
                .map(String::toLowerCase)
                .collect(toSet());

        if (results.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<GameResult> getResults() {
        return results;
    }


    public GameResult getFirstResult(){
        return getResults().get(INDEX_FIRST);
    }

    public GameResult getSecondResult(){
        return getResults().get(INDEX_SECOND);
    }
}