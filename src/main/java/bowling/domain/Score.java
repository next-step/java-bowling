package bowling.domain;

public class Score {
    private static final int NULL = -1;
    private static final Score nullScore = Score.of(NULL);
    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public static Score ofNull() {
        return nullScore;
    }

    public static Score ofPitch(Pitch pitch) {
        if (!pitch.isFinish()) {
            return Score.ofNull();
        }
        return Score.of(pitch.getFallenPin());
    }

    public Score add(Score other) {
        if (other.isNull()) {
            return other;
        }
        return Score.of(score + other.getScore());
    }

    public int getScore() {
        if (isNull()) {
            throw new IllegalArgumentException("null score can not getScore()");
        }
        return score;
    }

    public boolean isNull() {
        return Score.ofNull().equals(this);
    }

    @Override
    public String toString() {
        return score == NULL ? "" : String.valueOf(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
