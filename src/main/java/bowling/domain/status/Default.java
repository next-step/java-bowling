package bowling.domain.status;

import bowling.domain.Pitch;

public class Default implements Status {

    private Pitch current;

    public Default(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        if (current.isSpare(fallenPins)) {
            return new Spare();
        }

        return new Open();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }
}
