package game.bowling.domain.status;


import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/25.
 */
public class SecondThrow implements Status {

    private final int fallenPin;

    public SecondThrow(int fallenPin) {
        this.fallenPin = fallenPin;
    }

    @Override
    public Status bowl(int fallenPin) {
        if (this.fallenPin + fallenPin == 0) {
            return new Gutter();
        }

        if (this.fallenPin + fallenPin == 10) {
            return new Spare(this.fallenPin, fallenPin);
        }

        return new Miss(this.fallenPin, fallenPin);
    }

    @Override
    public Score getScore() {
        return new Score(fallenPin, null);
    }
}
