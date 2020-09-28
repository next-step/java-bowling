package bowling.domain.frame;

import bowling.domain.Swing;

public enum FrameState {

    RUNNING,
    END,
    SPARE,
    STRIKE;

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
        return this == FrameState.STRIKE || this == FrameState.SPARE;
    }

    public static FrameState afterUpdateState(FrameState state) {

        if (state == FrameState.STRIKE) {
            return FrameState.SPARE;
        }

        return FrameState.END;
    }
}
