package bowling.domain;

import java.util.Objects;

public class Scores {
    private Score firstScore;
    private Score secondScore;

    public Scores() { }

//    public void hitFirst(int hitCount) {
//        firstScore = new Score(hitCount);
//    }

//    public void hitSecond(int hitCount) {
//        secondScore = new Score(hitCount);
//    }

    public Score first() {
        return firstScore;
    }

    public Score second() {
        return secondScore;
    }

    private boolean isHitFirst() {
        return firstScore == null;
    }

    public boolean isHitTwice() {
        return firstScore != null && secondScore != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores = (Scores) o;
        return Objects.equals(firstScore, scores.firstScore) && Objects.equals(secondScore, scores.secondScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstScore, secondScore);
    }

    public void hit(int hitCount) {
        if (isHitFirst()) {
            firstScore = new Score(hitCount);
            return;
        }

        secondScore = new Score(hitCount);
    }
}
