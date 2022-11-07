package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultFrame implements Frame {

    private static final int MAX_SCORE = 10;

    private final List<Score> scores = new ArrayList<>();

    public DefaultFrame() {
    }

    public DefaultFrame(int first, int second) {
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
        validateSum();
    }

    private void validateSum() {
        if (this.scores.get(0).value() + this.scores.get(1).value() > MAX_SCORE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addScore(Score score) {
        if (!isRemainChance()) {
            throw new IllegalArgumentException("남은 기회가 없습니다.");
        }
        this.scores.add(score);
        if (this.scores.size() == 2) {
            validateSum();
        }
    }

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < 1) {
            return true;
        }
        if (!this.scores.get(0).isStrike() && this.scores.size() < 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultFrame that = (DefaultFrame) o;
        return Objects.equals(this.scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
