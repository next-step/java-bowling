package bowling.domain;

import bowling.exception.BowlingStateException;

import java.util.Objects;

public class Score {
    private int score;
    private int bonusCount;

    public Score(int score, int bonus){
        this.score = score;
        this.bonusCount = bonus;
    }

    public Score bowl(Pin pin) {
        this.score = this.score + pin.count();
        return new Score(pin.count(), bonusCount - 1);
    }

    public boolean finish(){
        return bonusCount == 0;
    }

    public static Score ofMiss(int firstCount, int secondCount){
        if(firstCount + secondCount > 10){
            throw new BowlingStateException("쓰러트린 볼링 핀의 총 합은 10을 넘을수 없습니다.");
        }
        return new Score(firstCount + secondCount, 0);
    }

    public static Score ofSpare(){
        return new Score(10, 1);
    }

    public static Score ofStrike(){
        return new Score(10, 2);
    }

    public int getScore() {
        return score;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return bonusCount == score1.bonusCount &&
                Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, bonusCount);
    }
}
