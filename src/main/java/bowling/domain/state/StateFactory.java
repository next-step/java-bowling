package bowling.domain.state;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.second.Normal;
import bowling.domain.state.end.second.Spare;
import bowling.domain.state.progress.GeneralProgress;
import bowling.domain.state.progress.Progress;
import bowling.domain.state.ready.Ready;

public class StateFactory {

    public static State ready() {
        return new Ready();
    }

    public static State progress(Frame frame, Pin pin, Progress progress) {
        return new GeneralProgress(frame, pin, progress);
    }

    public static State strike() {
        return new Strike();
    }


    public static State spare(GeneralProgress generalProgress) {
        return new Spare(generalProgress);
    }

    public static State normal(GeneralProgress generalProgress) {
        return new Normal(generalProgress);
    }

}
