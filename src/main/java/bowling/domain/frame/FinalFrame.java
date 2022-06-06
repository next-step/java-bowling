package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.Hit;
import bowling.domain.pin.Pins;
import bowling.exception.NotCreateFrameException;

import java.util.Objects;

public class FinalFrame implements Frame {

    private final Pins pins;
    private final Content content;

    public FinalFrame(Pins pins, Content content) {
        this.pins = pins;
        this.content = content;
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        throw new NotCreateFrameException(content.frameNo());
    }

    @Override
    public void bowling(int hit) {
        if (hasBonusChance()) {
            pins.fallDown(hit, true);
            return;
        }
        pins.fallDown(hit, false);
    }

    @Override
    public boolean isFinish() {
        if (hasBonusChance()) {
            return pins.hasThirdHit();
        }
        return pins.hasSecondHit();
    }

    private boolean hasBonusChance() {
        return pins.firstHit() == Hit.MAX_NUMBER || pins.firstHit() + pins.secondHit() == Hit.MAX_NUMBER;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Pins pins() {
        return pins;
    }

    @Override
    public Content content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pins, that.pins) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, content);
    }
}
