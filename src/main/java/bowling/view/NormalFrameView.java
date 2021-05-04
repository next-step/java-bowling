package bowling.view;

import bowling.domain.Frame;

import static bowling.util.BowlingFixture.*;

public final class NormalFrameView extends FrameView {

    private static final class NormalFrameViewHolder {
        private static final NormalFrameView instance = new NormalFrameView();
    }

    public static final FrameView getInstance() {
        return NormalFrameViewHolder.instance;
    }

    @Override
    protected final String formatFrame(final String frameResult) {
        return String.format(NORMAL_SCORE_FORMAT, frameResult) + DELIMITER;
    }

    @Override
    protected final String twiceScore(final Frame frame) {
        final String first = mapToSign(ZERO, frame.firstCount(), false);
        final String second = mapToSign(frame.firstCount(), frame.secondCount(), false);
        return first + DELIMITER + second;
    }

}
