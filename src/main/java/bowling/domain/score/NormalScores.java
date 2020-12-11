package bowling.domain.score;

import java.util.List;
import java.util.stream.IntStream;

public class NormalScores extends Scores {

    private NormalScores() {
        super();
    }

    public static Scores init() {
        return new NormalScores();
    }

    @Override
    public boolean canBowl() {
        if (scores.size() == SECOND_SCORE) {
            return false;
        }

        return !isStrike();
    }

    @Override
    public List<Score> getResult() {
        return scores;
    }

    @Override
    public Score getScore(int bonusScoreCount) {
        return IntStream.range(0, bonusScoreCount)
                .mapToObj(index -> scores.get(index))
                .reduce(Score.ZERO_SCORE, Score::sum);
    }
}