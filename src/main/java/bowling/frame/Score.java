package bowling.frame;

public class Score {
    private final int score;
    private final int left;

    public Score(int score) {
        this(score, 0);
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score + countOfPins, left - 1);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new CannotCalculateException();
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score miss(int totalPins) {
        return new Score(totalPins, 0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Score other = (Score) obj;
        if (left != other.left)
            return false;
        if (score != other.score)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Score [score=" + score + ", left=" + left + "]";
    }
}
