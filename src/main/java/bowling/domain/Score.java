package bowling.domain;

import java.util.Objects;

public class Score {

    private int firstPoint;
    private int secondPoint;

    private Score(int firstPoint, int secondPoint) {
        validatePoint(firstPoint, secondPoint);
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public static Score first(int firstPoint) {
        return of(firstPoint, 0);
    }

    public static Score of(int firstPoint, int secondPoint) {
        return new Score(firstPoint, secondPoint);
    }

    public Score second(int secondPoint) {
        return of(this.firstPoint, secondPoint);
    }

    private void validatePoint(int firstPoint, int secondPoint) {
        if (firstPoint + secondPoint > 10) {
            throw new IllegalArgumentException();
        }

        if (firstPoint < 0 || firstPoint > 10) {
            throw new IllegalArgumentException();
        }

        if (secondPoint < 0 || secondPoint > 10) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isSpare() {
        return this.firstPoint + this.secondPoint == 10;
    }

    public boolean isStrike() {
        return this.firstPoint == 10;
    }

    public boolean isMiss() {
        return this.firstPoint + this.secondPoint < 10;
    }

    public boolean isGutter() {
        return this.firstPoint != 10 && this.secondPoint == 0;
    }

    public int getFirstPoint() {
        return this.firstPoint;
    }

    public int getSecondPoint() {
        return this.secondPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return firstPoint == score.firstPoint &&
                secondPoint == score.secondPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPoint, secondPoint);
    }
}
