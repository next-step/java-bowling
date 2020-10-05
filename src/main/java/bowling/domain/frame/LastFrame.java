package bowling.domain.frame;

import bowling.domain.Swing;

public class LastFrame extends AbstractFrame {

    private FrameState state;

    public LastFrame() {
        state = FrameState.RUNNING;
    }

    @Override
    public void swing(int score) {
        super.swing(score);
        state = FrameState.forLastFrame(getSwing());
    }

    @Override
    public boolean isEndedFrame() {
        return state.isEndState();
    }

    @Override
    public int getScore() {

        if (state.isEndState()) {
            return getSwing().getScore();
        }

        return Swing.DUMMY_SCORE;
    }
}
