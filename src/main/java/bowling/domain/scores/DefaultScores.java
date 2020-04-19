package bowling.domain.scores;

import bowling.domain.score.DefaultScore;
import bowling.domain.score.Score;

public class DefaultScores extends Scores {
    @Override
    protected Score createScore(int point) {
        if (isFirstPlay()) {
            return DefaultScore.of(point);
        }
        return getLastScore().nextScore(point);
    }
}
