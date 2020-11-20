package bowling.domain.pin;

import bowling.domain.score.ScoreType2;

import java.util.ArrayList;
import java.util.List;

public class FinalFramePins implements Pins2 {
    public static final int MAXIMUM_PIN_NUMBER = 10;
    public static final int MINIMUM_PIN_NUMBER = 0;
    public static final String MINIMUM_PIN_ERROR = "핀이 0보다 작을 수 없습니다.";
    public static final String MAXIMUM_PIN_ERROR = "핀이 10보다 클 수 없습니다.";
    public static final String CAN_ROLL_ERROR = "더 이상 던질 수 없습니다";

    private List<Integer> downPins;
    private final Pins2 pins;

    public FinalFramePins(Pins2 pins) {
        this.pins = pins;
        this.downPins = new ArrayList<>();
    }

    public static Pins2 create() {
        return new FinalFramePins(new NormalFramePins());
    }

    @Override
    public void down(int pin) {
        validate(pin);
        if (this.pins.canRoll()) {
            this.pins.down(pin);
        }
        return;
    }

    private void validate(int pin) {
        if (pin < MINIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(MINIMUM_PIN_ERROR);
        }
        if (pin > MAXIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(MAXIMUM_PIN_ERROR);
        }
        if (!canRoll()) {
            throw new IllegalStateException(CAN_ROLL_ERROR);
        }
    }

    @Override
    public boolean canRoll() {
        if (this.pins.canRoll()) {
            return true;
        }
        return this.downPins.size() < getBonusCount();
    }

    @Override
    public ScoreType2 getScoreType() {
        return this.pins.getScoreType();
    }

    @Override
    public List<Integer> getDownPins() {
        List<Integer> downPins = new ArrayList<>(this.pins.getDownPins());
        downPins.addAll(this.downPins);
        return downPins;
    }

    @Override
    public int sum() {
        return this.pins.sum() + this.downPins.stream().mapToInt(Integer::intValue).sum();
    }

    private int getBonusCount() {
        return getScoreType().getBonusRollCount();
    }

}
