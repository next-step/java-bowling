package bowling.domain;

import bowling.exception.BowlingScoreException;

import java.util.Objects;

public class Score {
    private Pin pin;
    private int bonusCount;

    public Score(int pin, int bonus){
        this.pin = new Pin(pin);
        this.bonusCount = bonus;
    }

    public Score bowl(Pin pin) {
        this.pin = new Pin(this.pin.count() + pin.count());
        return new Score(pin.count(), bonusCount - 1);
    }

    public Pin sum(){
        if(!finish()){
            throw new BowlingScoreException("보너스 투구 후에 점수 합산이 가능합니다.");
        }
        return pin;
    }

    public boolean finish(){
        return bonusCount == 0;
    }

    public static Score ofMiss(int firstCount, int secondCount){
        return new Score(firstCount + secondCount, 0);
    }

    public static Score ofSpare(){
        return new Score(10, 1);
    }

    public static Score ofStrike(){
        return new Score(10, 2);
    }

    public Pin getPin() {
        return pin;
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
                Objects.equals(pin, score1.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, bonusCount);
    }
}
