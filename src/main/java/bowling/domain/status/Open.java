package bowling.domain.status;

import bowling.domain.Pitch;

public class Open implements Status {
    private final Pitch current;

    public Open(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        return null;
    }

    @Override
    public boolean isEnd() {
        return true;
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
