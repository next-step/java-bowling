package bowling.domain.status;

import bowling.domain.Pitch;

public class Default implements Status {
    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (pitch.isSpare2(fallenPins)) {
            return new Spare();
        }

        return new Open();
    }

    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
