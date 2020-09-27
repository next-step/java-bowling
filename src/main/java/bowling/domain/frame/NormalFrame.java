package bowling.domain.frame;

import bowling.domain.Swing;

public class NormalFrame extends AbstractFrame {

    private static final String ERR_NORMAL_FRAME_SCORE_RANGE = "Spare, Strike가 아니면 한 프레임에서 10점을 초과할 수 없습니다.";

    private FrameState state;

    public NormalFrame() {
        state = FrameState.RUNNING;
    }

    @Override
    public void swing(int score) {
        super.swing(score);
        Swing swing = getSwing();
        verifyScoreValue(swing);
        state = FrameState.forNormalFrame(swing);
    }

    private void verifyScoreValue(Swing swing) {
        if (swing.getScore() > Swing.MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_NORMAL_FRAME_SCORE_RANGE);
        }
    }

    @Override
    public boolean isEndedFrame() {
        return state.isNormalFrameEndState();
    }

    @Override
    public int getScore() {

        if (state.isEndState()) {
            return getSwing().getScore();
        }

        return Swing.DUMMY_SCORE;
    }
}
