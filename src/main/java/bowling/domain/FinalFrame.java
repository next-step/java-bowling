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
        this.score = new Score(this);
    }

    @Override
    public boolean canPitch() {
        return score.status().isKnockedDown() ||
                score.match(ScoreType.PROCEEDING);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }
}
