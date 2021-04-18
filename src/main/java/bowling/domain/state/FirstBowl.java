package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class FirstBowl implements State {

    private static final String INVALID_SCORE = "프레임이 종료된 후에 점수를 생성 할 수 있습니다.";
    private final Pin firstPin;

    public FirstBowl(int firstPin) {
        this(new Pin(firstPin));
    }

    public FirstBowl(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public State play(int fallenPin) {
        if (firstPin.isSpare(new Pin(fallenPin))) {
            return new Spare(firstPin, new Pin(fallenPin));
        }

        return new Miss(firstPin, new Pin(fallenPin));
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public int getTotalCount() {
        return firstPin.getCount();
    }

    @Override
    public String toString() {
        return firstPin.toString();
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new IllegalArgumentException(INVALID_SCORE);
    }

}
