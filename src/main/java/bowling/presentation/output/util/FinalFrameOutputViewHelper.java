package bowling.presentation.output.util;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FinalFrames;
import bowling.domain.frame.vo.FinalFrameScore;

import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameOutputViewHelper extends FrameOutputViewHelper {

    private static final int INDENT_SIZE = 5;

    protected FinalFrameOutputViewHelper() {
    }

    public static FinalFrameOutputViewHelper create() {
        return new FinalFrameOutputViewHelper();
    }

    public StringBuilder output(FinalFrames frames) {
        List<Integer> scores = frameScores(frames);
        return framesOutput(scores);
    }

    private List<Integer> frameScores(FinalFrames frames) {
        return frames.getAll().stream()
                .map(FinalFrame::score)
                .collect(Collectors.toList());
    }

    private StringBuilder framesOutput(List<Integer> scores) {

        if (scores.isEmpty()) {
            return new StringBuilder(EMPTY_FRAME);
        }

        FinalFrameScore finalFrameScore = FinalFrameScore.of(scores);
        return framesOutput(finalFrameScore);
    }

    private StringBuilder framesOutput(FinalFrameScore finalFrameScore) {

        StringBuilder scores = new StringBuilder();

        scores.append(first(finalFrameScore));
        if (isFirstTrial(finalFrameScore)) {
            return framesOutputFrom(scores);
        }

        scores.append(second(finalFrameScore));
        if (isSecondTrial(finalFrameScore)) {
            return framesOutputFrom(scores);
        }

        scores.append(third(finalFrameScore));
        return framesOutputFrom(scores);
    }

    private String first(FinalFrameScore finalFrameScore) {
        if (isStrike(finalFrameScore.getFirst())) {
            return STRIKE;
        }

        if (noPoints(finalFrameScore.getFirst())) {
            return NO_POINT;
        }

        return String.valueOf(finalFrameScore.getFirst());
    }

    private String second(FinalFrameScore score) {
        if (isStrike(score.getSecond())) {
            return BOUNDARY + STRIKE;
        }
        if (noPoints(score.getSecond())) {
            return BOUNDARY + NO_POINT;
        }

        if (isSpare(score.getFirst(), score.getSecond())) {
            return BOUNDARY + SPARE;
        }

        return BOUNDARY + score.getSecond();
    }

    private String third(FinalFrameScore score) {
        if (isStrike(score.getThird())) {
            return BOUNDARY + STRIKE;
        }
        if (noPoints(score.getThird())) {
            return BOUNDARY + NO_POINT;
        }
        return BOUNDARY + score.getThird();
    }

    private boolean isFirstTrial(FinalFrameScore finalFrameScore) {
        return finalFrameScore.getTrial() == 1;
    }

    private boolean isSecondTrial(FinalFrameScore finalFrameScore) {
        return finalFrameScore.getTrial() == 2;
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
