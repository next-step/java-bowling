package bowling.presentation.output.util;

import bowling.domain.score.NormalScore;

public class ScoreOutputHelper {

    protected static final String BOUNDARY = "|";

    protected static final String STRIKE = "X";

    protected static final String SPARE = "/";

    protected static final String NO_POINT = "-";

    private ScoreOutputHelper() {
    }

    public static ScoreOutputHelper create() {
        return new ScoreOutputHelper();
    }

    public String first(NormalScore score) {
        if (score.isStrike()) {
            return STRIKE;
        }

        if (score.isFirstTryNoPoint()) {
            return NO_POINT;
        }
        return String.valueOf(score.getFirst());
    }

    public String second(NormalScore score) {

        if (!score.isDone()) {
            return "";
        }

        if (score.isStrike()) {
            return "";
        }

        if (score.isSpare()) {
            return BOUNDARY + SPARE;
        }

        if (score.isSecondTryNoPoint()) {
            return BOUNDARY + NO_POINT;
        }

        return BOUNDARY + score.getSecond();
    }

}
