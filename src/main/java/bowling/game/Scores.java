package bowling.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scores {
    private final List<Score> scores;

    public Scores(final List<Score> scores) {
        this.scores = scores;
    }

    public List<Integer> getFrameScores() {
        List<Integer> frameScores = new ArrayList<>();
        int sum = 0;

        for (Score score : scores) {
            sum += score.getScore();

            frameScores.add(sum);
        }

        return frameScores;
    }
}
