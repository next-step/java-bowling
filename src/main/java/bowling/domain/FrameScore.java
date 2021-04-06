package bowling.domain;

public class FrameScore {
    private static final String IS_NOT_END = "해당 프레임이 종료되지 않았습니다.";
    private final int score;
    private final BonusChance bonusChance;

    private FrameScore(int score, BonusChance bonusChance) {
        this.score = score;
        this.bonusChance = bonusChance;
    }

    public static FrameScore of(Frame frame) {
        validFrameIsEnd(frame);
        return new FrameScore(frame.score(), BonusChance.of(frame.bonusChance()));
    }

    public FrameScore add(int countOfDownPin) {
        if (!bonusChance.isScoreAdd()) {
            return this;
        }
        return new FrameScore(score + countOfDownPin, bonusChance.minus());
    }

    public boolean isFrameScoreEnd() {
        return bonusChance.isScoreAdd();
    }

    public int score() {
        return score;
    }

    private static void validFrameIsEnd(Frame frame) {
        if (!frame.isEnd()) {
            throw new IllegalArgumentException(IS_NOT_END);
        }
    }
}
