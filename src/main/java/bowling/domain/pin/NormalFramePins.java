package bowling.domain.pin;

import bowling.domain.score.ScoreType2;

import java.util.ArrayList;
import java.util.List;

public class NormalFramePins implements Pins2 {

    private static final int MAX_PIN_COUNT = 10;
    public static final int MINIMUM_PIN_NUMBER = 0;
    private static final int MAX_ROUND = 2;

    private static final int STRIKE_POINT = 10;
    private static final int FIRST_ROLL = 1;
    private static final int SECOND_ROLL = 2;

    public static final String PIN_MIN_ERROR = "볼링 핀은 최초 0 미만이 될 수 없습니다. ";
    public static final String PIN_VALIDATION_ERROR = "핀의 갯수가 유효하지 않습니다";
    public static final String ROLL_LIMIT_ERROR = "더 이상 던질 수 없습니다";
    public static final int FIRST_ROUND_PINS = 0;
    public static final int SPARE_POINT = 10;

    private List<Integer> downPins = new ArrayList<>();

    @Override
    public void down(int pin) {
        validate(pin);
        this.downPins.add(pin);
    }

    private void validate(int pin) {
        if (pin < MINIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_MIN_ERROR);
        }
        if (totalDownPins() + pin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_VALIDATION_ERROR);
        }
        if (!canRoll()) {
            throw new IllegalStateException(ROLL_LIMIT_ERROR);
        }
    }

    @Override
    public boolean canRoll() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }
        if (totalDownPins() == MAX_PIN_COUNT) {
            return false;
        }
        return true;
    }

    @Override
    public ScoreType2 getScoreType() {
        if (isStrike()) {
            return ScoreType2.STRIKE;
        }
        if (isSpare()) {
            return ScoreType2.SPARE;
        }
        if (!canRoll()) {
            return ScoreType2.MISS;
        }
        return ScoreType2.READY;
    }

    @Override
    public List<Integer> getDownPins() {
        return new ArrayList<>(downPins);
    }

    @Override
    public int sum() {
        return this.downPins.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isStrike() {
        if (this.downPins.size() == FIRST_ROLL && this.downPins.get(FIRST_ROUND_PINS) == STRIKE_POINT) {
            return true;
        }
        return false;
    }

    private boolean isSpare() {
        if (this.downPins.size() == SECOND_ROLL && totalDownPins() == SPARE_POINT) {
            return true;
        }
        return false;
    }

    private int totalDownPins() {
        return this.downPins.stream().mapToInt(Integer::intValue).sum();
    }
}
