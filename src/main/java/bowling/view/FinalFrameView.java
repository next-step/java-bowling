package bowling.view;

import bowling.domain.Frame;

import static bowling.util.BowlingFixture.*;

public final class FinalFrameView extends FrameView {

    private static final class FinalFrameViewHolder {
        private static final FinalFrameView instance = new FinalFrameView();
    }

    public static final FrameView getInstance() {
        return FinalFrameViewHolder.instance;
    }

    protected String formatFrame(String frameResult) {
        return String.format(FINAL_SCORE_FORMAT, frameResult) + DELIMITER + NEW_LINE;
    }

    @Override
    protected final String twiceScore(Frame frame) {
        final String first = mapToSign(ZERO, frame.firstCount(), true);
        final String second = mapToSign(frame.firstCount(), frame.secondCount(), true);
        return first + DELIMITER + second;
    }

}
