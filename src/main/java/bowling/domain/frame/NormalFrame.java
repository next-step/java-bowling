package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreConverter;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int FIRST_FRAME = 0;

    private Pins pins;
    private Score score;

    public NormalFrame(final int index) {
        this.pins = new Pins();
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    @Override
    public void roll(Pin pin) {
        if (canRoll()) {
            pins.addPins(this, pin.getPin());
        }
    }

    @Override
    public boolean canRoll() {
        return pins.isPinReady(this);
    }

    @Override
    public boolean isGameOver() {
        if (!canRoll()) {
            return false;
        }
        return true;
    }

    @Override
    public int getTotal() {
        return pins.getTotalPins();
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
    public String getStringScore() {
        return ScoreConverter.convert(this);
    }

    public Integer getScore() {
        try {
            return score.getScore();
        }catch (Exception e) {
            return null;
        }
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
        if (!canRoll()) {
            this.score = convertScore();
        }
    }

    private Score convertScore() {
        if (pins.isStrike()) {
            return Score.ofStrike();
        }
        if (pins.isSpare()) {
            return Score.ofSpare();
        }
        return Score.ofMiss(pins.getTotalPins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(getPins(), that.getPins()) &&
                Objects.equals(getScore(), that.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPins(), getScore());
    }
}
