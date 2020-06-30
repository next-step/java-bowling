package bowling.domain.frame;

import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int LIMIT_COUNT = 2;

    List<Score> scores = new ArrayList<>();

    public void addScore(int score) {
        scores.add(new Score(score));
    }

    public boolean isStrike() {
        return scores.get((scores.size() - 1)).validateMaxScore();
    }

    public boolean isMiss() {
        return scores.get((scores.size() - 1)).validateMinScore();
    }

    public boolean isSpare() {
        if(scores.size() < LIMIT_COUNT) {
            return false;
        }
        return scores.get((scores.size() - 2)).getScore()
                + scores.get((scores.size() - 1)).getScore()
                == Score.MAX_SCORE;
    }

    public int getFrameLastScore() {
        return scores.get((scores.size() - 1)).getScore();
    }

    public int moveNextFrame() {
        return 1;
    }
}
