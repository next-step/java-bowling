package bowling.domain.frame;

import bowling.constant.Score;
import bowling.domain.Content;
import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.domain.pin.Pins;
import bowling.exception.NotCreateFrameException;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Pins pins;
    private final Content content;

    private NormalFrame(Content content) {
        this.pins = new NormalFramePins();
        this.content = content;
    }

    public static NormalFrame initialize() {
        return new NormalFrame(Content.initialize());
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        if (content.isNextFrameNoLast()) {
            return last();
        }
        return new NormalFrame(content.next(Score.of(this)));
    }

    private Frame last() {
        return new FinalFrame(new FinalFramePins(), content.next(Score.of(this)));
    }

    @Override
    public void bowling(int hit) {
        pins.fallDown(hit, false);
    }

    @Override
    public boolean isFinish() {
        return Score.of(pins).isStrike() || pins.hasSecondHit();
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
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(pins, that.pins) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, content);
    }
}
