package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.Opened;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.PinEndState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class Frame {

    protected final Progress progress;

    protected final List<EndState> results;

    protected Frame() {
        this(ProgressFactory.create(), new ArrayList<>());
    }

    protected Frame(Progress progress, List<EndState> results) {
        this.progress = progress;
        this.results = results;
    }

    public abstract Frame bowl(Pin pin);

    protected abstract Progress nextProgress(EndState endState);

    protected abstract boolean isNextAbleState(EndState endState);

    protected abstract Optional<Frame> next();

    public boolean isOpened() {
        return this.progress instanceof Opened;
    }

    protected boolean isClosed() {
        return !isOpened();
    }

    protected Progress searchNextProgress(Pin pin) {
        EndState endState = ((Opened) progress).pitch(pin);
        results.add(endState);

        return nextProgress(endState);
    }

    protected int sumHitPinsCount(Predicate<? super EndState> predicate) {
        return results.stream()
            .filter(result -> result.isInstanceOf(PinEndState.class))
            .filter(predicate)
            .mapToInt(result -> ((PinEndState) result).getHitPinCount())
            .sum();
    }

    protected int sumHitPinsCount() {
        return sumHitPinsCount(result -> true);
    }

    public Progress getProgress() {
        return progress;
    }

    public List<EndState> getResults() {
        return Collections.unmodifiableList(results);
    }
}
