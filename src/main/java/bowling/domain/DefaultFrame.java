package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultFrame implements Frame {

    private static final int DEFAULT_FRAME_SIZE = 2;
    private final Scores scores;

    public DefaultFrame() {
        this.scores = new Scores();

    }

    public DefaultFrame(int first, int second) {
        this.scores = new Scores(Score.of(first), Score.of(second));
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
        if (this.scores.isEmpty()) {
            return true;
        }
        return isRemainSecondChance();
    }

    private boolean isRemainSecondChance() {
        return (!this.scores.first().isStrike()) && (this.scores.size() < DEFAULT_FRAME_SIZE);
    }

    @Override
    public List<Score> scores() {
        return Collections.unmodifiableList(this.scores.scores());
    }

    @Override
    public Scores scores2() {
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
        DefaultFrame that = (DefaultFrame) o;
        return Objects.equals(this.scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
