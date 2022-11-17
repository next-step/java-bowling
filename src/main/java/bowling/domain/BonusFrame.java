package bowling.domain;

import java.util.ArrayList;

public class BonusFrame implements Frame {
    public static final int BONUS_ROLL = 3;

    private final Rolls rolls;
    private FrameStatus status;

    private BonusFrame(Rolls rolls, FrameStatus status) {
        this.rolls = rolls;
        this.status = status;
    }

    public BonusFrame() {
        this(new Rolls(new ArrayList<>()), FrameStatus.PROGRESS);
    }

    public BonusFrame(Rolls rolls) {
        this(rolls, FrameStatus.PROGRESS);
    }

    @Override
    public boolean isEnd() {
        if (rolls.size() == BONUS_ROLL) {
            return true;
        }
        return status == FrameStatus.MISS;
    }

    @Override
    public void updateStatus() {
        if (rolls.size() < BONUS_ROLL) {
            status = FrameStatus.match(rolls);
        }
    }

    @Override
    public void addRoll(Pin pin) {
        rolls.add(pin);
    }

    @Override
    public Rolls getScores() {
        return rolls;
    }

    @Override
    public FrameStatus getStatus() {
        return status;
    }

    @Override
    public Score calculateScore() {
        return Score.end(rolls.sum());
    }
}
