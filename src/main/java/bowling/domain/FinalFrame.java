package bowling.domain;

public class FinalFrame extends Frame {
    private static final int DEFAULT_FRAME_NUMBER = 10;

    public FinalFrame() {
        this(10);
    }

    public FinalFrame(int number) {
        if (number != DEFAULT_FRAME_NUMBER) {
            throw new IllegalArgumentException("마지막 프레임의 번호는 " + DEFAULT_FRAME_NUMBER + "이어야 합니다.");
        }

        this.number = number;
        this.score = new Score(this);
    }

    @Override
    public boolean canPitch() {
        return (score.status().equals(ScoreType.STRIKE) && score.pins().size() == 1) ||
                (score.status().equals(ScoreType.SPARE) && score.pins().size() == 2) ||
                score.status().equals(ScoreType.PROCEEDING);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }
}
