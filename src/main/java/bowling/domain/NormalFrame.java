package bowling.domain;

public class NormalFrame extends Frame {
    private static final int MAX_TRYABLE_COUNT = 2;

    public NormalFrame(int frameNo) {
        super(frameNo);
    }

    @Override
    public boolean isThrowable() {
        if (points.getTryCount() >= MAX_TRYABLE_COUNT || points.isFirstStrike()) {
            return false;
        }
        return true;
    }
}
