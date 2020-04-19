package bowling.domain.scores;

import bowling.domain.score.LastScore;
import bowling.domain.score.Score;

public class LastScores extends Scores {
    @Override
    protected Score createScore(int point) {
        if (isFirstPlay()) {
            return LastScore.of(point);
        }
        return getLastScore().nextScore(point);
    }
}
