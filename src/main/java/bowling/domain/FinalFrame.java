package bowling.domain;

public class FinalFrame extends Frame {
    private static final int DEFAULT_FRAME_NUMBER = 10;

    private Pin thirdPin;
    protected int chance = 3;

    public FinalFrame(int number) {
        if (number != DEFAULT_FRAME_NUMBER) {
            throw new IllegalArgumentException("마지막 프레임의 번호는 " + DEFAULT_FRAME_NUMBER + "이어야 합니다.");
        }

        this.number = number;
    }

    @Override
    public void fitch(int number) {
        if (hasNoMoreChance()) {
            throw new IllegalStateException("더 이상 던질 수 없습니다.");
        }

        if (isFirst()) {
            firstPin = Pin.of(number);
            chance -= 1;
            return;
        }

        if (isStrike()) {
            secondPin = Pin.of(number);
            chance -= 2;
            return;
        }

        if (chance == 2) {
            secondPin = Pin.of(number);
            chance -= 1;
            return;
        }

        thirdPin = Pin.of(number);
        chance -= 1;
    }

    @Override
    public boolean isFirst() {
        return chance == 3;
    }

    private boolean hasNoMoreChance() {
        return chance == 0;
    }
}
