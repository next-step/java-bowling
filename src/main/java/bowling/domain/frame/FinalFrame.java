package bowling.domain.frame;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNo) {
        super(frameNo);
    }

    @Override
    public boolean isThrowable() {
        if (points.getTryCount() == 0 || points.getTryCount() == 1) {
            return true;
        }
        if (points.isSpare() || points.isDoubleStrike()) {
            return true;
        }
        return false;
    }
}
