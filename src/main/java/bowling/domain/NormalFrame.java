package bowling.domain;

public class NormalFrame extends Frame {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;

    public NormalFrame(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }

        this.number = number;
    }

    @Override
    public void fitch(int number) {
        if (hasNoMoreChance() || isStrike()) {
            throw new IllegalStateException("더 이상 던질 수 없습니다.");
        }

        if (isFirst()) {
            firstPin = Pin.of(number);
            chance -= 1;
            return;
        }

        secondPin = Pin.of(number);
        chance -= 1;
    }

    @Override
    public boolean isFirst() {
        return chance == 2;
    }

    private boolean hasNoMoreChance() {
        return chance == 0;
    }
}
