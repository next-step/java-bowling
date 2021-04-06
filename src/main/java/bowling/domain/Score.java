package bowling.domain;

public class Score {
    private static final String IS_NOT_END = "해당 프레임이 종료되지 않았습니다.";
    private final int score;
    private final BonusChance bonusChance;

    private Score(int score, BonusChance bonusChance) {
        this.score = score;
        this.bonusChance = bonusChance;
    }

    public static Score of(Frame frame) {
        validFrameIsEnd(frame);
        return new Score(frame.score(), BonusChance.of(frame.bonusChance()));
    }

    public Score add(int countOfDownPin) {
        if (!bonusChance.isScoreAdd()) {
            return this;
        }
        return new Score(score + countOfDownPin, bonusChance.minus());
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
