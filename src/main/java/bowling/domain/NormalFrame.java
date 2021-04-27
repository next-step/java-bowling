package bowling.domain;

public class NormalFrame extends Frame {

    private static final int EMPTY_FRAME = 0;
    private static final int FULL_FRAME = 2;
    private static final int FINAL_NORMAL_FRAME = 9;
    private static final int CALCULATION_NOT_COMPLETED = -1;
    private static final int REQUIRED_PIN_COUNT = 2;

    public boolean hasSecond() {
        return !pinNumbers.index(0).isStrike();
   }

    @Override
    public boolean hasNext() {
        if (pinNumbers.size() == FULL_FRAME) {
            return false;
        }
        return pinNumbers.size() == EMPTY_FRAME || hasSecond();
    }

    @Override
    public FrameStrategy nextFrame(int frameNumber) {
        if (frameNumber == FINAL_NORMAL_FRAME) {
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame();
        return next;
    }

    @Override
    public int score() {
        if (hasNext()) {
            return CALCULATION_NOT_COMPLETED;
        }
        return FrameResult.scoreByResult(this, result(size()));
    }

    public int scoreInStrike() {
        if (next.hasNext() && next.pinNumbers.size() < REQUIRED_PIN_COUNT) {
            return CALCULATION_NOT_COMPLETED;
        }
        if (next.pinNumbers.index(0).isStrike()) {
            return scoreInDouble();
        }
        return pinNumbers.sum() + next.pinNumbers.sum();
    }

    private int scoreInDouble() {
        if (next.next == null) {
            return scoreByFinalFrame();
        }
        if (next.next.pinNumbers.size() == EMPTY_FRAME) {
            return CALCULATION_NOT_COMPLETED;
        }
        return pinNumbers.sum() + next.pinNumbers.sum() + next.next.pinNumbers.index(0).pinNumber();
    }

    private int scoreByFinalFrame() {
        if (next.pinNumbers.size() < REQUIRED_PIN_COUNT) {
            return CALCULATION_NOT_COMPLETED;
        }
        return pinNumbers.sum() + next.pinNumbers.index(0).pinNumber() + next.pinNumbers.index(1).pinNumber();
    }

    public int scoreInSpare() {
        if (next.pinNumbers.size() == EMPTY_FRAME) {
            return CALCULATION_NOT_COMPLETED;
        }
        return pinNumbers.sum() + next.pinNumbers.index(0).pinNumber();
    }

    public int scoreInMiss() {
        return pinNumbers.sum();
    }
}
