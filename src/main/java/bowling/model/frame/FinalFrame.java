package bowling.model.frame;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private Score bonusScore;

    public FinalFrame(FrameNumber number, FrameScore score) {
        super(number, score);
    }

    private FinalFrame(FrameNumber number, FrameScore score, int bonusScore) {
        super(number, score);
        this.bonusScore = Score.of(bonusScore);
    }

    public FinalFrame(int number, int firstScore) {
        super(new FrameNumber(number), FrameScore.first(firstScore));
    }

    public FinalFrame(int number, int firstScore, int secondScore) {
        super(number, firstScore, secondScore);
    }

    public FinalFrame(int number, int firstScore, int secondScore, int bonusScore) {
        super(number, firstScore, secondScore);
        this.bonusScore = Score.of(bonusScore);
    }

    @Override
    protected void validateNumberRange(FrameNumber number) {
        if (!number.isEqual(FINAL_FRAME_NUMBER)) {
            throw new IllegalArgumentException(String.format("마지막 프레임 번호는 %d 이어야 합니다.", FINAL_FRAME_NUMBER));
        }
    }

    @Override
    public boolean canPlayNext() {
        return bonusScore == null && (score.isFirst() || isStrikeOrSpare());
    }

    @Override
    public Frame next(int score) {
        if (isFirstAndNotStrike()) {
            return new FinalFrame(number, this.score.second(score));
        }

        if (isStrikeOrSpare()) {
            return new FinalFrame(number, this.score, score);
        }

        throw new IllegalArgumentException("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @Override
    public FrameNumber nextNumber() {
        if (isFirstAndNotStrike() || isStrikeOrSpare()) {
            return number;
        }
        throw new IllegalArgumentException("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @Override
    public boolean isBonusPlay() {
        return bonusScore != null;
    }

    @Override
    public Score getBonusScore() {
        return bonusScore;
    }
}
