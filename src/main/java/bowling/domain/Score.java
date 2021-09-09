package bowling.domain;

import java.util.Objects;

public class Score {
    private final int score;
    private int left;

    private Score(int score, int left){
        this.score = score;
        this.left = left;
    }

    public static Score of(int score, int left) {
        return new Score(score, left);
    }

    public Score additionalScore(Pins pins) {
        if(canCalculate()){
            return new Score(pins.sumOfScore(score), 0);
        }
        return new Score(pins.sumOfScore(score), left - 1);
    }

    public Score add(int value) {
        return new Score(score + value, left);
    }

    public boolean canCalculate(){
        return left == 0;
    }

    public int getScore(){
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
