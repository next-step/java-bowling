package bowling.domain.score;

public class Score {
    private final static int MAX_SCORE = 10;
    private final static int MIN_SCORE = 0;

    private int point;

    public Score(int point) {
        this.validatePoint(point);
        this.point = point;
    }

    private void validatePoint(int point) {
        if (point > MAX_SCORE || point < MIN_SCORE) {
            throw new IllegalArgumentException("not valid point");
        }
    }

    public int getPoint() {
        return point;
    }
}
