package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {

    private List<Score> scores = new LinkedList<>();

    public static Score createScore(State state, Pins pins) {
        if (state == State.SPARE) {
            return new ScoreSpare(pins);
        }
        if (state == State.STRIKE) {
            return new ScoreStrike(pins);
        }
        return new ScoreNormal(pins);
    }

    public List<Score> add(Score score) {
        scores.add(score);
        return scores;
    }

    public List<String> showGameScore() {
        return scores.stream()
                .map(Score::getCurrentScore)
                .collect(Collectors.toList());
    }

    public List<String> showGameSumScore() {
        reset();
        for (int i = 0; i < scores.size() - 1; i++) {
            scores.get(i).calculateAdditionalScore(scores.get(i + 1));
        }
        // last one
        scores.get(scores.size() - 1).calculateAdditionalScore(scores.get(scores.size() - 1));
        return scores.stream()
                .map(Score::getCurrentSumScore)
                .collect(Collectors.toList());
    }

    private void reset() {
        scores.stream().forEach(x -> x.reset());
    }

}
