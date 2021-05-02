package bowling.view;

import bowling.domain.score.Score;
import bowling.view.ui.Cell;

public final class ScoreView {

    private static final String EMPTY_STRING = "";

    private final Score score;
    private int totalCount;

    public ScoreView(Score frame, int totalCount) {
        this.score = frame;
        this.totalCount = totalCount;
    }

    public int totalCount() {
        if (score.canCalculate()) {
            totalCount += score.calculate();
        }
        return totalCount;
    }

    public Cell cell() {
        return Cell.center(totalScore());
    }

    private String totalScore() {
        if (score.canCalculate()) {
            return String.valueOf(totalCount);
        }
        return EMPTY_STRING;
    }
}
