package bowling.domain.frame;

import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class Frame implements FrameInterface{

    private static final int LIMIT_COUNT = 2;
    private static final int FIRST_COUNT = 0;

    List<Score> scores = new ArrayList<>();

    @Override
    public void addScore(int score) {
        scores.add(new Score(score));
    }

    @Override
    public boolean validateLimitScore() {
        return scores.size() == LIMIT_COUNT || hasStrike();
    }

    public boolean hasStrike() {
        return scores.get(FIRST_COUNT).validateMaxScore();
    }

}
