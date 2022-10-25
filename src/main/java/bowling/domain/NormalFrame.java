package bowling.domain;

public class NormalFrame extends Frame {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;

    public NormalFrame() {
        this(1);
    }

    public NormalFrame(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }

        this.number = number;
        this.score = new Score(this);
    }

    @Override
    public void pitch(int number) {
        try {
            score.addPin(Pin.of(number));
        } catch (RuntimeException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public boolean canPitch() {
        return score.status().equals(ScoreType.PROCEEDING);
    }

    @Override
    public Frame nextFrame() {
        if (number == MAX_FRAME_NUMBER) {
            return new FinalFrame(number + 1);
        }

        return new NormalFrame(number + 1);
    }
}
