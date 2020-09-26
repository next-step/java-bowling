package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.ScoreConverter;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME = 10;

    private Pins pins;
    private int index;

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
        return this.pins.getTotalPins();
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
    public String getScore() {
        return ScoreConverter.convert(this);
    }
}
