package bowling.domain;

import java.util.ArrayList;

public class BonusFrame implements Frame {
    private final Rolls rolls;
    private FrameResult result;

    public BonusFrame() {
        this.rolls = new Rolls(new ArrayList<>());
    }

    public BonusFrame(Rolls rolls) {
        this.rolls = rolls;
    }

    @Override
    public boolean end() {
        if (rolls.size() == 3) {
            return true;
        }

        if (rolls.sum().equals(new Pin(10)) || rolls.size() == 2) {
            result = FrameResult.match(rolls);
            return !isNeedBonus();
        }

        return false;
    }

    private boolean isNeedBonus() {
        return result == FrameResult.STRIKE || result == FrameResult.SPARE;
    }

    @Override
    public void addScore(Pin pin) {
        rolls.add(pin);
    }

    @Override
    public Rolls getScores() {
        return rolls;
    }

    @Override
    public FrameResult getResult() {
        return result;
    }
}
