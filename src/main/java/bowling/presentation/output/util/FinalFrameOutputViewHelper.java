package bowling.presentation.output.util;

import bowling.domain.frame.FinalFrame;
import bowling.domain.score.FinalScore;

public class FinalFrameOutputViewHelper extends FrameOutputViewHelper {

    private static final int INDENT_SIZE = 5;

    protected FinalFrameOutputViewHelper() {
    }

    public static FinalFrameOutputViewHelper create() {
        return new FinalFrameOutputViewHelper();
    }

    public StringBuilder output(FinalFrame frame) {

        FinalScore score = frame.getScore();

        StringBuilder scores = new StringBuilder();

        scores.append(first(score));
        if (isFirstTrial(frame.getTrial())) {
            return framesOutputFrom(scores);
        }

        scores.append(second(score));
        if (isSecondTrial(frame.getTrial())) {
            return framesOutputFrom(scores);
        }

        scores.append(third(score));
        return framesOutputFrom(scores);
    }

    private String first(FinalScore score) {
        int first = score.getFirst();
        if (isStrike(first)) {
            return STRIKE;
        }

        if (noPoints(first)) {
            return NO_POINT;
        }

        return String.valueOf(first);
    }

    private String second(FinalScore score) {
        int second = score.getSecond();

        if (isStrike(second)) {
            return BOUNDARY + STRIKE;
        }
        if (noPoints(second)) {
            return BOUNDARY + NO_POINT;
        }

        if (score.isSpare()) {
            return BOUNDARY + SPARE;
        }

        return BOUNDARY + score.getSecond();
    }

    private String third(FinalScore score) {
        if (isStrike(score.getThird())) {
            return BOUNDARY + STRIKE;
        }
        if (noPoints(score.getThird())) {
            return BOUNDARY + NO_POINT;
        }
        return BOUNDARY + score.getThird();
    }

    private boolean isFirstTrial(int trial) {
        return trial == 1;
    }

    private boolean isSecondTrial(int trial) {
        return trial == 2;
    }

    private StringBuilder framesOutputFrom(StringBuilder outputScores) {
        StringBuilder framesOutput = new StringBuilder();
        framesOutput.append(BOUNDARY).append(SPACE).append(outputScores);
        setOutputIndent(framesOutput, outputScores);
        return framesOutput;
    }

    private void setOutputIndent(StringBuilder framesOutput, StringBuilder outputScores) {
        for (int i = outputScores.length(); i < INDENT_SIZE; i++) {
            framesOutput.append(SPACE);
        }
    }

}
