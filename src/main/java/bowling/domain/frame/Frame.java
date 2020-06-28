package bowling.domain.frame;

import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int FIRST_COUNT = 0;
    private static final int SECOND_COUNT = 1;

    List<Score> scores = new ArrayList<>();

    public void addScore(int score) {
        scores.add(new Score(score));
    }

    public boolean isStrike() {
        return scores.get(FIRST_COUNT).validateMaxScore();
    }

    public boolean isSpare() {
        return scores.get(FIRST_COUNT).getScore()
                + scores.get(SECOND_COUNT).getScore()
                == Score.MAX_SCORE;
    }

    public boolean validateLimitScore() {
        return true;
    }
}
