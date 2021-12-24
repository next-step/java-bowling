package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.Opened;
import bowling.domain.state.end.EndState;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Frame {

    protected Progress progress = ProgressFactory.create();

    protected List<EndState> results = new ArrayList<>();

    protected Frame() {
    }

    public abstract Progress bowl(Pin pin);

    protected abstract Progress nextProgress(EndState endState);

    protected Progress searchNextProgress(Pin pin) {
        EndState endState = ((Opened) progress).pitch(pin);
        results.add(endState);

        Progress progress = nextProgress(endState);
        this.progress = progress;
        return progress;
    }


    protected abstract Optional<Frame> next();


    public List<EndState> getResults() {
        return Collections.unmodifiableList(results);
    }
}
