package bowling.domain.status;

import bowling.domain.Pitch;

public class Ready implements Status {
    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (pitch.isStrike2()) {
            return new Strike();
        }

        return new Default();
    }

    @Override
    public String display(int fallenPins) {
        return null;
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
