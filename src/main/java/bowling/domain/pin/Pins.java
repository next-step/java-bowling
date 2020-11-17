package bowling.domain.pin;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Score;
import bowling.domain.state.ScoreType;


import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final java.lang.String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";
    private static final java.lang.String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final int NORMAL_FRAME_CAN_ROLL = 2;
    private static final int FINAL_FRAME_CAN_ROLL = 3;
    private static final int FIRST_ROLL = 1;
    private static final int SECOND_ROLL = 2;
    private static final int PINS_LIMIT = 10;
    private static final int PINS_DOUBLE_STRIKE = 20;
    private static final int PINS_GUTTER = 0;

    private List<Pin> pins;
    private ScoreType scoreType;

    public Pins() {
        this.pins = new ArrayList<>();
        this.scoreType = ScoreType.NONE;
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
        this.scoreType = ScoreType.NONE;
    }

    public void addPins(Frame frame, int pin) {
        if (frame instanceof NormalFrame && isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }
        if (frame instanceof FinalFrame && isPinOverflowRolledOnce(pin)) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
        this.scoreType = scoreCheck();
        this.pins.add(new Pin(pin));
    }

    public ScoreType getScoreType() {
        scoreType = scoreCheck();
        return scoreType;
    }

    public boolean isPinReady(Frame frame) {
        if (frame instanceof NormalFrame) {
            return !isStrike() && !isRolledTwice();
        }
        if (!isRolledTwice() || getTotalPins() >= PINS_LIMIT) {
            return pins.size() < FINAL_FRAME_CAN_ROLL;
        }
        return false;
    }

    public int getTotalPins() {
        return this.pins.stream()
                .map(Pin::getPin)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean isPinOverflowRolledOnce(int pin) {
        return isRolledOnce() && (isPinUnderTen()) && (getTotalPins() + pin) > PINS_LIMIT;
    }

    public int getLeftPin() {
        return PINS_LIMIT - getTotalPins();
    }

    public boolean isStrike() {
        return pins.size() == FIRST_ROLL && this.getTotalPins() == PINS_LIMIT;
    }

    public boolean isDoubleStrike() {
        return pins.size() == SECOND_ROLL && this.getTotalPins() == PINS_DOUBLE_STRIKE;
    }

    public boolean isGutter() {
        return this.getTotalPins() == PINS_GUTTER;
    }

    public boolean isSpare() {
        return pins.size() == SECOND_ROLL && getTotalPins() == PINS_LIMIT;
    }

    public boolean isFinalSpare() {
        return pins.size() == SECOND_ROLL && this.isSpare() && !this.isStrike();
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getTotalPins() + pin > PINS_LIMIT;
    }

    public boolean isRolledOnce() {
        return pins.size() == FIRST_ROLL;
    }

    public boolean isRolledTwice() {
        return pins.size() == NORMAL_FRAME_CAN_ROLL;
    }

    public boolean isRolledThird() {
        return pins.size() == FINAL_FRAME_CAN_ROLL;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public int getPinCount(int index) {
        return pins.get(index).getPin();
    }

    public int rollCount() {
        return pins.size();
    }

    private boolean isPinUnderTen() {
        return getTotalPins() < PINS_LIMIT;
    }

    public Pin getPin(int index) {
        return pins.get(index);
    }

    public Pin getFirstPin() {
        return pins.get(0);
    }

    public Pin getSecondPin() {
        return pins.get(1);
    }

    public Pin getThirdPin() {
        return pins.get(2);
    }

    public Score convertScore() {
        if (ScoreType.STRIKE.equals(scoreType)) {
            return Score.ofStrike();
        }
        if (ScoreType.SPARE.equals(scoreType)) {
            return Score.ofSpare();
        }
        return Score.ofMiss(getTotalPins());
    }

    public ScoreType scoreCheck() {
        if (isStrike() || isDoubleStrike()) {
            return ScoreType.STRIKE;
        }
        if (isSpare()) {
            return ScoreType.SPARE;
        }
        if (isGutter()) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }

}
