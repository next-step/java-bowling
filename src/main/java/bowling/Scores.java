package bowling;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final int SECOND_SCORE = 2;

    List<Score> scores = new ArrayList<>();

    public void add(Score score) {
        scores.add(score);
    }

    public boolean isEnd() {
        return isMaxScore() || isFull();
    }

    public FrameResult getFrameResult() {
        if (isMaxScore()) {
            return getSpareOrStrike();
        }

        return FrameResult.MISS;
    }

    private boolean isFull() {
        return scores.size() >= SECOND_SCORE;
    }

    private Boolean isMaxScore() {
        return scores.stream()
                .reduce(Score::sum)
                .map(Score::isMaxScore)
                .orElse(false);
    }

    private FrameResult getSpareOrStrike() {
        if (isFull()) {
            return FrameResult.SPARE;
        }

        return FrameResult.STRIKE;
    }
}
