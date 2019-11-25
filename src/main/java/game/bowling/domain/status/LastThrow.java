package game.bowling.domain.status;

import game.bowling.domain.FinalScore;
import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/26.
 */
public class LastThrow implements Status {

    private final int firstFallenPin;
    private final int secondFallenPin;
    private int thirdFallenPin;

    public LastThrow(int firstFallenPin, int secondFallenPin) {
        this.firstFallenPin = firstFallenPin;
        this.secondFallenPin = secondFallenPin;
    }

    @Override
    public Status bowl(int fallenPin) {
        this.thirdFallenPin = fallenPin;
        return new Finished(firstFallenPin, secondFallenPin, thirdFallenPin);
    }

    @Override
    public Score getScore() {
        return new FinalScore(firstFallenPin, secondFallenPin, null);
    }
}
