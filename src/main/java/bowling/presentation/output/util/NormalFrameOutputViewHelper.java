package bowling.presentation.output.util;

import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.domain.frame.vo.NormalFrameScore;

import java.util.List;
import java.util.stream.Collectors;

public class NormalFrameOutputViewHelper extends FrameOutputViewHelper {

    private static final int INDENT_SIZE = 4;

    private static final int FIRST_FRAME = 1;

    private static final int LAST_FRAME = 9;

    private NormalFrameOutputViewHelper() {
    }

    public static NormalFrameOutputViewHelper create() {
        return new NormalFrameOutputViewHelper();
    }

    public StringBuilder output(NormalFrames frames) {

        StringBuilder framesOutput = new StringBuilder();

        for (int i = FIRST_FRAME; i <= LAST_FRAME; i++) {
            List<Integer> scores = frameScores(frames, i);
            setFrameOutputScores(framesOutput, scores);
        }

        return framesOutput;
    }

    private List<Integer> frameScores(NormalFrames frames, int i) {
        return frames.findByFrame(i).getAll().stream()
                .map(NormalFrame::score)
                .collect(Collectors.toList());
    }

    private void setFrameOutputScores(StringBuilder framesOutput, List<Integer> scores) {
        if (scores.isEmpty()) {
            framesOutput.append(EMPTY_FRAME);
            return;
        }

        NormalFrameScore normalFrameScore = NormalFrameScore.of(scores);

        StringBuilder outputScores = new StringBuilder();

        outputScores
                .append(first(normalFrameScore))
                .append(second(normalFrameScore));

        framesOutput
                .append(BOUNDARY)
                .append(SPACE).append(SPACE)
                .append(outputScores);

        setOutputIndent(framesOutput, outputScores);
    }


    private String first(NormalFrameScore score) {
        if (isStrike(score.getFirst())) {
            return STRIKE;
        }

        if (noPoints(score.getFirst())) {
            return NO_POINT;
        }
        return String.valueOf(score.getFirst());
    }

    private String second(NormalFrameScore score) {

        if (score.isFirstTry()) {
            return "";
        }

        if (isStrike(score.getFirst())) {
            return "";
        }

        if (isSpare(score.getFirst(), score.getSecond())) {
            return BOUNDARY + SPARE;
        }

        if (noPoints(score.getSecond())) {
            return BOUNDARY + NO_POINT;
        }

        return BOUNDARY + score.getSecond();
    }


    private void setOutputIndent(StringBuilder framesOutput, StringBuilder outputScores) {
        for (int i = outputScores.length(); i < INDENT_SIZE; i++) {
            framesOutput.append(SPACE);
        }
    }
}
