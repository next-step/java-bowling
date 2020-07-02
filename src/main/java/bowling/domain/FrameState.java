package bowling.domain;

public class FrameState {

    private final int remainPinCount;
    private final boolean newState;
    private final boolean finishState;

    public FrameState(int remainPinCount, boolean newState, boolean finishState) {
        this.remainPinCount = remainPinCount;
        this.newState = newState;
        this.finishState = finishState;
    }

    public static FrameState ofNew() {
        return new FrameState(Pin.MAX_COUNT, true, false);
    }

    public static FrameState ofNotFinish(int remainPinCount) {
        return new FrameState(remainPinCount, false, false);
    }

    public static FrameState ofFinish() {
        return new FrameState(0, false, true);
    }

    public int getRemainPinCount() {
        return remainPinCount;
    }

    public boolean isNew() {
        return newState;
    }

    public boolean isFinish() {
        return finishState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FrameState that = (FrameState) o;

        if (remainPinCount != that.remainPinCount) return false;
        if (newState != that.newState) return false;
        return finishState == that.finishState;
    }

    @Override
    public int hashCode() {
        int result = remainPinCount;
        result = 31 * result + (newState ? 1 : 0);
        result = 31 * result + (finishState ? 1 : 0);
        return result;
    }
}
