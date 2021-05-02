package bowling.domain.status;

import bowling.domain.Pitch;

public class Final implements Status {
    private final Pitch current;
    private final int remainBonusCount;

    public Final(Pitch current, int remainBonusCount) {
        this.current = current;
        this.remainBonusCount = remainBonusCount;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);
        return new Final(pitch, remainBonusCount - 1);
    }

    @Override
    public boolean isEnd() {
        return remainBonusCount == 0;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display(int fallenPins) {
        return null;
    }
}
