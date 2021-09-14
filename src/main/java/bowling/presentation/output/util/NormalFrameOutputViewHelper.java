package bowling.presentation.output.util;

import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.domain.score.NormalScore;

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
            setFrameOutputScores(framesOutput, frameScores(frames, i));
        }

        return framesOutput;
    }

    private NormalScore frameScores(NormalFrames frames, int idx) {
        return frames.findByFrame(idx).getAll().stream()
                .map(NormalFrame::score)
                .findFirst()
                .orElse(NormalScore.none());
    }

    private void setFrameOutputScores(StringBuilder framesOutput, NormalScore score) {
        if (NormalScore.isNone(score)) {
            framesOutput.append(EMPTY_FRAME);
            return;
        }

        ScoreOutputHelper helper = ScoreOutputHelper.create();
        StringBuilder outputScores = new StringBuilder();

        outputScores
                .append(helper.first(score))
                .append(helper.second(score));

        framesOutput
                .append(BOUNDARY)
                .append(SPACE).append(SPACE)
                .append(outputScores);

        setOutputIndent(framesOutput, outputScores);
    }

    private void setOutputIndent(StringBuilder framesOutput, StringBuilder outputScores) {
        for (int i = outputScores.length(); i < INDENT_SIZE; i++) {
            framesOutput.append(SPACE);
        }
    }
}
