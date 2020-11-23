package bowling.frame.state;

import bowling.score.Pin;
import bowling.score.Score;

import java.util.List;

public abstract class State {

    public abstract State bowl(Pin fellPins);

    public abstract List<String> getBowlResults();

    public abstract boolean isFinish();

    public abstract Score getScore();

    public Score calculateScore(Score previousScore) {
        if (previousScore.isCalculateScore()) {
            return previousScore;
        }
        return getScore().calculate(previousScore);
    }

}
