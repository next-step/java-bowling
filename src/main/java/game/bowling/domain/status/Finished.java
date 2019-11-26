package game.bowling.domain.status;

import game.bowling.domain.FinalScore;
import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/26.
 */
public class Finished implements LastStatus {

    private final int firstFallenPin;
    private final int secondFallenPin;
    private final int thirdFallenPin;

    public Finished(int firstFallenPin, int secondFallenPin, int thirdFallenPin) {
        this.firstFallenPin = firstFallenPin;
        this.secondFallenPin = secondFallenPin;
        this.thirdFallenPin = thirdFallenPin;
    }

    @Override
    public Status bowl(int fallenPin) {
        return null;
    }

    @Override
    public Score getScore() {
        return new FinalScore(firstFallenPin, secondFallenPin, thirdFallenPin);
    }
}
