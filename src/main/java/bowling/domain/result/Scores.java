package bowling.domain.result;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Scores {
    List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores.stream()
                .map(score -> score.getScore())
                .collect(toList());
    }
}
