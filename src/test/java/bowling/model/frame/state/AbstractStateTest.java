package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import jdk.nashorn.internal.ir.annotations.Ignore;

@Ignore
public class AbstractStateTest {

    protected static State ofGutter() {
        return Gutter.getInstance();
    }

    protected static State ofHit(Pins downPins) {
        return Hit.valueOf(downPins);
    }

    protected static State ofStrike() {
        return Strike.getInstance();
    }

    protected static Score scoreOf(int count, int score) {
        return Score.of(count, score);
    }
}
