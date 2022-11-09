package bowling.domain;

import bowling.global.BowlingConst;
import java.util.Objects;

public class LastFrame implements Frame {

    private static final int LAST_FRAME_SIZE = 3;
    private final Scores scores;

    public LastFrame() {
        this.scores = new Scores();
    }

    public LastFrame(int first, int second, int third) {
        this.scores = new Scores(Score.of(first), Score.of(second), Score.of(third));
        ScoreValidator.validate(this);
    }

    @Override
    public void addScore(Score score) {
        if (isRemainChance()) {
            this.scores.add(score);
            ScoreValidator.validate(this);
            return;
        }
        throw new IllegalArgumentException("남은 기회가 없습니다.");
    }

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < LAST_FRAME_SIZE - 1) {
            return true;
        }
        if (this.scores.size() == LAST_FRAME_SIZE - 1) {
            return isRemainThirdTimeChance();
        }
        return false;
    }

    private boolean isRemainThirdTimeChance() {
        Score first = this.scores.first();
        Score second = this.scores.second();
        if (first.isStrike() || second.isStrike() || ((first.value() + second.value()) == BowlingConst.SCORE_STRIKE)) {
            return true;
        }
        return false;
    }

    @Override
    public Scores scores() {
        return this.scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(this.scores, lastFrame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
