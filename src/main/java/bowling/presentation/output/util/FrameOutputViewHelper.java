package bowling.presentation.output.util;

public class FrameOutputViewHelper {

    protected static final String BOUNDARY = "|";

    protected static final String SPACE = " ";

    protected static final String STRIKE = "X";

    protected static final String SPARE = "/";

    protected static final String NO_POINT = "-";

    protected static final String EMPTY_FRAME = "|      ";

    protected boolean isStrike(int score) {
        return score == 10;
    }

    protected boolean isSpare(int first, int second) {
        return first != 10 && first + second == 10;
    }

    protected boolean noPoints(int score) {
        return score == 0;
    }
}
