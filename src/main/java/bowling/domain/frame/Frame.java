package bowling.domain.frame;

import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame {

    protected static final int LIMIT_COUNT = 2;

    List<Score> scores = new ArrayList<>();

    public void addScore(int score) {
        scores.add(new Score(score, scores));
    }

    public boolean isFirst() {
        return scores.size() == 0;
    }

    public boolean isStrike() {
        return scores.get((0)).validateMaxScore();
    }

    public boolean isMiss() {
        return scores.get((scores.size() - 1)).validateMinScore();
    }

    public boolean isSpare() {
        if(scores.size() < LIMIT_COUNT) {
            return false;
        }
        return scores.get(0).getScore()
                + scores.get(1).getScore()
                == Score.MAX_SCORE;
    }

    public int getFrameLastScore() {
        return scores.get((scores.size() - 1)).getScore();
    }

    public List<Score> getFrameScore() {
        return Collections.unmodifiableList(scores);
    }

    public int getScore() {
        return 0;
    }

    public int moveNextFrame() {
        return 1;
    }
}
