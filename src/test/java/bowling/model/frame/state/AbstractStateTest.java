package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import jdk.nashorn.internal.ir.annotations.Ignore;

@Ignore
public class AbstractStateTest {

    protected static State ofGutter() {
        return Gutter.getInstance();
    }

    protected static State ofHit(DownPin downPin) {
        return Hit.valueOf(downPin);
    }

    protected static State ofStrike() {
        return Strike.getInstance();
    }

    protected static Score scoreOf(int count, int score) {
        return Score.of(count, score);
    }

}
