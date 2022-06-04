package bowling.domain.frame;

import bowling.constant.Score;
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
        if (Score.of(pins).isStrike() || Score.of(pins).isSpare()) {
            pins.fallDown(hit, true);
            return;
        }
        pins.fallDown(hit, false);
    }

    @Override
    public boolean isFinish() {
        if (Score.of(pins).isStrike() || Score.of(pins).isSpare()) {
            return pins.hasThirdHit();
        }
        return pins.hasSecondHit();
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
