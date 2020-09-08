package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    public static final int BEGIN_STAGE = 1;
    public static final int END_STAGE = 10;
    public static final int BONUS_STAGE = END_STAGE;

    private static final int FINAL_STEP = 2;
    private static final int BONUS_STEP = FINAL_STEP + 1;

    private int stage;
    private int step;
    private Pins pins;
    private Pins bonusPins;
    private List<GameResult> results;

    private Frame(int stage) {
        if (stage < BEGIN_STAGE) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정될수 없습니다. [%s]", BEGIN_STAGE, stage));
        }

        if (stage > END_STAGE) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정될수 없습니다. [%s]", END_STAGE, stage));
        }

        this.stage = stage;
        this.step = 1;
        this.pins = Pins.newPins();
        this.bonusPins = Pins.newPins();
        this.results = new ArrayList<>();
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

    public GameResult record(int hitCount) {
        if (hasBonusStep()) {
            return bonusRecord(hitCount);
        }

        return hitting(pins, hitCount);
    }

    private GameResult bonusRecord(int hitCount) {
        step = BONUS_STEP;
        return hitting(bonusPins, hitCount);
    }

    private GameResult hitting(Pins pins, int hitCount) {
        pins.hitting(hitCount);
        GameResult result = toResult(step, pins);
        step++;

        this.results.add(result);

        return result;
    }

    private GameResult toResult(int step, Pins pins) {
        if (pins.checkCount(Pins.DEFAULT_PIN_COUNT)) {
            return GameResult.GUTTER;
        }

        if (step == 1 && pins.isClear()) {
            return GameResult.STRIKE;
        }

        if (pins.isClear()) {
            return GameResult.SPARE;
        }

        return GameResult.MISS;
    }
}
