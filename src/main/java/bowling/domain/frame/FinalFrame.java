package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreConverter;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME = 10;
    private static final int MAXIMUM_BONUS_COUNT = 3;
    private static final int MAXIMUM_NORMAL_COUNT = 2;

    private Pins pins;
    private int index;
    private Score score;

    public FinalFrame() {
        this.pins = new Pins();
        this.index = 10;
    }

    @Override
    public void roll(int pin) {
        if (canRoll()) {
            pins.addPins(this, pin);
        }
    }

    @Override
    public boolean canRoll() {
        return pins.isPinReady(this);
    }

    @Override
    public int getTotal() {
        return pins.getTotalPins();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public List<Pin> getPinInfo() {
        return pins.getPins();
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public boolean isGameOver() {
        return index == FINAL_FRAME && canRoll();
    }

    @Override
    public String getStringScore() {
        return ScoreConverter.convert(this);
    }

    public Integer getScore() {
        return score.getScore();
    }

    public void calculateScore(int point) {
        if (score != null) {
            this.score = score.calculate(point);
        }
    }

    public boolean hasScore() {
        if (score != null) {
            return true;
        }
        return false;
    }

    @Override
    public void addScore() {
        if (pins.isStrike() || pins.isSpare()) {
            this.score = convertScore();
        }
    }

    private Score convertScore() {
        if (pins.isStrike()) {
            return Score.ofStrike();
        }
        if (pins.rollCount() == 2 && pins.isSpare() && !pins.isStrike()) {
            return Score.ofSpare();
        }
        return Score.ofMiss(pins.getTotalPins());
    }
}
