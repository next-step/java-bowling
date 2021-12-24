package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.progress.Opened;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.PinEndState;
import bowling.domain.state.end.first.Gutter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public abstract class Frame {

    protected static final int GENERAL_ROUND_NUMBER = 2;

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

    public boolean isOpened() {
        return this.progress instanceof Opened;
    }

    protected boolean isClosed() {
        return !isOpened();
    }

    protected Progress searchNextProgress(Pin pin) {
        EndState endState = ((Opened) progress).pitch(pin);

        if (isMiss(endState)) {
            clearAndAddResultMiss();
            return nextProgress(StateFactory.miss());
        }

        results.add(endState);

        return nextProgress(endState);
    }

    private void clearAndAddResultMiss() {
        results.clear();
        results.add(StateFactory.miss());
    }

    protected int sumHitPinsCount(Predicate<? super EndState> predicate) {
        return results.stream()
            .filter(result -> result.isInstanceOf(PinEndState.class))
            .filter(predicate)
            .mapToInt(result -> ((PinEndState) result).getHitPinCount())
            .sum();
    }

    protected boolean isMiss(EndState endState) {
        if (results.isEmpty()) {
            return false;
        }

        if (!endState.isInstanceOf(Gutter.class)) {
            return false;
        }

        return results.stream()
            .allMatch(result -> result.isInstanceOf(Gutter.class));
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
