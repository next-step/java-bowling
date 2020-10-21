package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreConverter;

import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private Pins pins;
    private Score score;

    public FinalFrame() {
        this.pins = new Pins();
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
    public boolean isGameOver() {
        return canRoll();
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
        return score != null;
    }

    @Override
    public void addScore() {
        if (pins.isStrike() || pins.isSpare()) {
            this.score = pins.convertScore();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(getPins(), that.getPins()) &&
                Objects.equals(getScore(), that.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPins(), getScore());
    }
}
