package bowling.domain;

public abstract class Frame {
    protected final BowlingPin bowlingPin;
    protected int availability;

    protected Frame() {
        bowlingPin = new BowlingPin();
    }

    public int addScore(int inputScore) {
        if (isAvailable()) {
            --availability;
            bowlingPin.update(inputScore);
            updateCondition(inputScore);
            return inputScore;
        }
        throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
    }

    protected abstract void updateCondition(int inputScore);

    public abstract Frame createFrame(int frameNumber);

    public boolean isAvailable() {
        if (availability > 0) {
            return true;
        }
        return false;
    }
}
