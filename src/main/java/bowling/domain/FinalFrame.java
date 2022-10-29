package bowling.domain;

public class FinalFrame extends Frame {
    public static final int MAX_FRAME_NUMBER = 10;

    public FinalFrame() {
        this(10);
    }

    public FinalFrame(int number) {
        if (number != MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("마지막 프레임의 번호는 " + MAX_FRAME_NUMBER + "이어야 합니다.");
        }

        this.number = number;
        this.score = new Score();
    }

    @Override
    public boolean isEnd() {
        return !canPitch();
    }

    @Override
    public boolean canPitch() {
        return score.pinsSize() < 2 ||
                (score.pinsSize() != 3 && status().isKnockedDowned()) ||
                (score.pinsSize() == 2 && score.pinNumber(0) == 10);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }
}
