package bowling.model.frame;

public class NormalFrame extends Frame {
    private static final int MIN_NORMAL_NUMBER = 1;
    private static final int MAX_NORMAL_NUMBER = 9;

    public NormalFrame(FrameNumber number, FrameScore score) {
        super(number, score);
    }

    public NormalFrame(int number, int firstScore, int secondScore) {
        super(number, firstScore, secondScore);
    }

    public NormalFrame(int number, int firstScore) {
        super(new FrameNumber(number), FrameScore.first(firstScore));
    }

    private boolean isNextFinalFrame() {
        return needNextNumber() && number.isNextFinal();
    }

    private boolean needNextNumber() {
        return isStrike() || pitchTwice();
    }

    @Override
    protected void validateNumberRange(FrameNumber number) {
        if (number.isUnder(MIN_NORMAL_NUMBER) || number.isOver(MAX_NORMAL_NUMBER)) {
            throw new IllegalArgumentException(String.format("노말 프레임 번호는 %d 이상 %d 이하 이어야 합니다.",
                    MIN_NORMAL_NUMBER, MAX_NORMAL_NUMBER));
        }
    }

    @Override
    public boolean canPlayNext() {
        return (score.isFirst() && !isStrike()) || number.canMakeNext();
    }

    @Override
    public Frame next(int score) {
        if (isNextFinalFrame()) {
            return new FinalFrame(number.next(), FrameScore.first(score));
        }

        if (needNextNumber()) {
            return new NormalFrame(number.next(), FrameScore.first(score));
        }

        return new NormalFrame(number, this.score.second(score));
    }
}
