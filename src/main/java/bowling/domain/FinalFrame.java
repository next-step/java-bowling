package bowling.domain;

public class FinalFrame implements Frame {
    private static final String CANNOT_THROW_THIRD_BOWL = "3구를 던질수 없습니다.";
    private static final int FINAL_INDEX = 9;

    private final FrameBowls frameBowls;

    public FinalFrame() {
        this(new FinalFrameBowls());
    }

    private FinalFrame(FrameBowls frameBowls) {
        this.frameBowls = frameBowls;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (!frameBowls.isFirstBowlThrown()) {
            return firstBowl(pinCount);
        }

        if (!frameBowls.isSecondBowlThrown()) {
            return secondBowl(pinCount);
        }

        if (isThirdThrowPossible()) {
            return thirdBowl(pinCount);
        }

        throw new IllegalArgumentException(CANNOT_THROW_THIRD_BOWL);
    }

    private boolean isThirdThrowPossible() {
        return frameBowls.isFirstBowlStrike() || frameBowls.isSecondBowlSpare();
    }

    public Frame throwBowl(int pinCount) {
        return throwBowl(String.valueOf(pinCount));
    }

    private Frame firstBowl(int firstPinCount) {
        return new FinalFrame(new FinalFrameBowls().firstThrow(firstPinCount));
    }

    private Frame firstBowl(String firstPinCount) {
        return firstBowl(Integer.parseInt(firstPinCount));
    }

    private Frame secondBowl(int secondPinCount) {
        return new FinalFrame(frameBowls.secondThrow(secondPinCount));
    }

    private Frame secondBowl(String secondPinCount) {
        return secondBowl(Integer.parseInt(secondPinCount));
    }

    private FinalFrame thirdBowl(int thirdPinCount) {
        FinalFrameBowls frameBowls = (FinalFrameBowls) this.frameBowls;
        return new FinalFrame(frameBowls.thirdThrow(thirdPinCount));
    }

    private FinalFrame thirdBowl(String thirdPinCount) {
        return thirdBowl(Integer.parseInt(thirdPinCount));
    }

    @Override
    public boolean isFinished() {
        FinalFrameBowls bowls = (FinalFrameBowls) frameBowls;

        if (isThirdThrowPossible() && bowls.isThirdBowlThrown())
            return true;

        if (isThirdThrowPossible() && !bowls.isThirdBowlThrown()) {
            return false;
        }

        return (!isThirdThrowPossible()) && bowls.isSecondBowlThrown();
    }

    @Override
    public int index() {
        return FINAL_INDEX;
    }

    @Override
    public FrameBowls bowls() {
        return frameBowls;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public String toString() {
        FinalFrameBowls bowls = (FinalFrameBowls) this.frameBowls;

        if (isAllNotThrown()) {
            return "      ";
        }

        if (!bowls.isFirstBowlStrike() && bowls.isSecondBowlSpare() && !bowls.isThirdBowlThrown()) {
            return " " + bowls.firstPinCount().toString() + "|" + "/" + " ";
        }

        if (isFirstAndSecondThrown() && !isThirdThrowPossible()) {
            return " " + bowls.firstPinCount().toString() + "|" + bowls.secondPinCount().toString() + "  ";
        }

        if (isFirstAndSecondThrown() && isThirdThrowPossible() && !bowls.isThirdBowlThrown()) {
            return " " + bowls.firstPinCount().toString() + "|" + bowls.secondPinCount().toString() + "  ";
        }

        if (isOnlyFirstThrown()) {
            return " " + bowls.firstPinCount().toString() + "    ";
        }

        return " " + bowls.firstPinCount().toString() + "|" + bowls.secondPinCount().toString() + "|" + bowls.thirdPinCount().toString();
    }

    private boolean isFirstAndSecondThrown() {
        return frameBowls.isFirstBowlThrown() && frameBowls.isSecondBowlThrown();
    }

    private boolean isOnlyFirstThrown() {
        return frameBowls.isFirstBowlThrown() && !frameBowls.isSecondBowlThrown();
    }

    private boolean isAllNotThrown() {
        FinalFrameBowls bowls = (FinalFrameBowls) this.frameBowls;
        return !bowls.isFirstBowlThrown() && !bowls.isSecondBowlSpare() && !bowls.isThirdBowlThrown();
    }
}
