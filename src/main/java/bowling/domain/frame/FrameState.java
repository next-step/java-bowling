package bowling.domain.frame;

import bowling.domain.Swing;

public enum FrameState {

    RUNNING(0),
    END(0),
    SPARE(1),
    STRIKE(2);

    private final int updateNeedCount;

    FrameState(int updateNeedCount) {
        this.updateNeedCount = updateNeedCount;
    }

    public static FrameState forNormalFrame(Swing swing) {

        if (swing.isSpare()) {
            return SPARE;
        }

        if (swing.isStrike()) {
            return STRIKE;
        }

        if (swing.isNormalFrameEnd()) {
            return END;
        }

        return RUNNING;
    }

    public static FrameState forLastFrame(Swing swing) {

        if (swing.isLastFrameEnd()) {
            return END;
        }

        return RUNNING;
    }

    public boolean isNormalFrameEndState() {
        return this != FrameState.RUNNING;
    }

    public boolean isEndState() {
        return this == FrameState.END;
    }

    public boolean needUpdate() {
        return updateNeedCount > 0;
    }

    public static FrameState afterUpdateState(FrameState state) {

        if (state == FrameState.STRIKE) {
            return FrameState.SPARE;
        }

        return FrameState.END;
    }
}
