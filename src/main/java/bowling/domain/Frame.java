package bowling.domain;

import java.util.Objects;

public abstract class Frame {

    protected final Scores scores;

    public Frame() {
        this.scores = new Scores();
    }

    public void addScore(Score score) {
        if (isRemainChance()) {
            this.scores.add(score);
            ScoreValidator.validate(this);
            return;
        }
        throw new IllegalArgumentException("남은 기회가 없습니다.");
    }

    public Scores scores() {
        return this.scores;
    }

    abstract boolean isRemainChance();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(this.scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
