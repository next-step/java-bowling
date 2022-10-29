package bowling.domain;

public class NormalFrame extends Frame {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;

    public NormalFrame() {
        this(1);
    }

    public NormalFrame(int number) {
        checkSize(number);

        this.number = number;
        this.score = new Score();
    }

    private void checkSize(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }
    }

    @Override
    public boolean isEnd() {
        return !canPitch();
    }

    @Override
    public boolean canPitch() {
        return score.pinsSize() != 2 && !status().isKnockedDowned();
    }

    @Override
    public Frame nextFrame() {
        if (number == MAX_FRAME_NUMBER) {
            return new FinalFrame(number + 1);
        }

        return new NormalFrame(number + 1);
    }
}
