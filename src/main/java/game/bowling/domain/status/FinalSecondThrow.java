package game.bowling.domain.status;

import game.bowling.domain.FinalScore;
import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/26.
 */
public class FinalSecondThrow implements Status {

    private final int fallenPin;

    public FinalSecondThrow(int fallenPin) {
        this.fallenPin = fallenPin;
    }

    @Override
    public Status bowl(int fallenPin) {
        if (this.fallenPin + fallenPin == 0) {
            return new Gutter();
        }

        if (this.fallenPin + fallenPin < 10) {
            return new Miss(this.fallenPin, fallenPin);
        }

        return new LastThrow(this.fallenPin, fallenPin);
    }

    @Override
    public Score getScore() {
        return new FinalScore(fallenPin, null, null);
    }
}
