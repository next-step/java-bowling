package bowling.domain;

public class Frame {

    public static final int BEGIN_STAGE = 1;
    public static final int END_STAGE = 10;
    public static final int BONUS_STAGE = END_STAGE;

    private static final int FIRST_STEP = 1;
    private static final int FINAL_STEP = 2;
    private static final int BONUS_STEP = FINAL_STEP + 1;

    private int stage;
    private int step;
    private Pins pins;
    private Pins bonusPins;
    private GameResults results;

    private Frame(int stage) {
        if (stage < BEGIN_STAGE) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정될수 없습니다. [%s]", BEGIN_STAGE, stage));
        }

        if (stage > END_STAGE) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정될수 없습니다. [%s]", END_STAGE, stage));
        }

        this.stage = stage;
        this.step = FIRST_STEP;
        this.pins = Pins.create();
        this.bonusPins = Pins.create();
        this.results = GameResults.create();
    }

    public static Frame from(int stage) {
        return new Frame(stage);
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
        return stage == BONUS_STAGE && pins.isClear() && step <= BONUS_STEP;
    }

    public void record(int hitCount) {
        if (hasBonusStep()) {
            bonusRecord(hitCount);
            return;
        }

        hitting(pins, hitCount);
    }

    private void bonusRecord(int hitCount) {
        step = BONUS_STEP;
        hitting(bonusPins, hitCount);
    }

    private void hitting(Pins pins, int hitCount) {
        pins.hitting(hitCount);
        results.record(toResult(step, hitCount, pins));
        step++;
    }

    private GameResult toResult(int step, int hitCount, Pins pins) {
        if (pins.checkCount(Pins.COUNT)) {
            return GameResult.ofGutter();
        }

        if (stage == BONUS_STAGE && step == BONUS_STEP && pins.isClear()) {
            return GameResult.ofStrike();
        }

        if (step == FIRST_STEP && pins.isClear()) {
            return GameResult.ofStrike();
        }

        if (pins.isClear()) {
            return GameResult.ofSpare();
        }

        return GameResult.ofMiss(hitCount);
    }

    public int getStage() {
        return stage;
    }

    public GameResults getResults() {
        return results;
    }
}
