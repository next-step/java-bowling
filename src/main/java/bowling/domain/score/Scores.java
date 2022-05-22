package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

    private final List<Score> scores;

    private Scores(List<Score> scores) {
        this.scores = Collections.unmodifiableList(scores);
    }

    public static Scores create(List<Score> scores) {
        return new Scores(scores);
    }


    public List<Integer> accumulateScore() {
        List<Integer> sumScores = new ArrayList<>();
        int sum = 0;

        for (Score score : scores) {
            if(score.isUnavailable()) {
                sumScores.add(score.score());
                continue;
            }

            sum += score.score();
            sumScores.add(sum);
        }

        return sumScores;
    }

}
