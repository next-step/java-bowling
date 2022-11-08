package bowling.domain;

import bowling.global.BowlingConst;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LastFrame implements Frame {

    private static final int LAST_FRAME_SIZE = 3;
    private final List<Score> scores = new ArrayList<>();

    public LastFrame() {

    }

    public LastFrame(int first, int second, int third) {
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
        this.scores.add(Score.of(third));
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
        Score first = this.scores.get(0);
        Score second = this.scores.get(1);
        if (first.isStrike() || second.isStrike() || ((first.value() + second.value()) == BowlingConst.SCORE_STRIKE)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Score> scores() {
        return Collections.unmodifiableList(this.scores);
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
