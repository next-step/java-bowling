package game.bowling.domain.status;

import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/25.
 */
public class Spare implements LastStatus {

    private final int firstFallenPin;
    private final int secondFallenPin;

    public Spare(int firstFallenPin, int secondFallenPin) {
        this.firstFallenPin = firstFallenPin;
        this.secondFallenPin = secondFallenPin;
    }

    @Override
    public Status bowl(int fallenPin) {
        return new FirstThrow();
    }

    @Override
    public Score getScore() {
        return new Score(firstFallenPin, secondFallenPin);
    }

}
