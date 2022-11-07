package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultFrame implements Frame {

    private final List<Score> scores = new ArrayList<>();

    public DefaultFrame() {

    }

    public DefaultFrame(int first, int second) {
        this.scores.add(Score.of(first));
        this.scores.add(Score.of(second));
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
        if (this.scores.size() < 1) {
            return true;
        }
        if (!this.scores.get(0).isStrike() && this.scores.size() < 2) {
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
        DefaultFrame that = (DefaultFrame) o;
        return Objects.equals(this.scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
