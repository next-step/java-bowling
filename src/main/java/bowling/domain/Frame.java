package bowling.domain;

public class Frame {
    private static final int FINAL_STEP = 2;
    private static final int BONUS_STEP = FINAL_STEP + 1;

    private int stage;
    private int step;
    private Pins pins;
    private Pins bonusPins;

    public Frame(int stage) {
        this.stage = stage;
        this.step = 1;
        this.pins = Pins.newPins();
        this.bonusPins = Pins.newPins();
    }

    public boolean hasNextTurn() {
        if (hasBonusStep()) {
            return true;
        }

        if (step > FINAL_STEP) {
            return false;
        }

        return !pins.isClear();
    }

    private boolean hasBonusStep() {
        return stage == 10 && pins.isClear() && step < BONUS_STEP;
    }

    public void record(int hitCount) {
        if (hasBonusStep()) {
            bonusPins.hitting(hitCount);
            step++;
            return;
        }

        pins.hitting(hitCount);
        step++;
    }
}
