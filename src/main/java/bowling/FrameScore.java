package bowling;

import static bowling.CommonConstans.IS_NOT_END;

public class FrameScore {

    private final Score score;
    private final BonusChance bonusChance;

    private FrameScore(Score score, BonusChance bonusChance) {
        this.score = score;
        this.bonusChance = bonusChance;
    }

    public static FrameScore of(Frame frame) {
        validFrameIsEnd(frame);
        return new FrameScore(frame.score(), frame.bonusChance());
    }

    public FrameScore addScore(int countOfDownPin) {
        if (!bonusChance.isAddScore()) {
            return this;
        }
        return new FrameScore(score.add(countOfDownPin), bonusChance.minus());
    }

    public boolean isAggregateEnd() {
        return bonusChance.isAddScore();
    }

    public int aggregateScore() {
        return score.score();
    }

    private static void validFrameIsEnd(Frame frame) {
        if (!frame.isEnd()) {
            throw new IllegalArgumentException(IS_NOT_END);
        }
    }
}
