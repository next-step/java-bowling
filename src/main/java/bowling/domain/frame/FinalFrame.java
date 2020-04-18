package bowling.domain.frame;

public class FinalFrame extends Frame {

    public FinalFrame() {
        super();
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
