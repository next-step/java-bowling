package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.progress.Opened;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.PinEndState;
import bowling.domain.state.end.Results;
import bowling.domain.state.end.first.Gutter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public abstract class Frame {

    protected static final int GENERAL_ROUND_NUMBER = 2;

    protected final Progress progress;
    protected final Results results;

    protected Frame() {
        this(ProgressFactory.create(), new Results());
    }


    protected Frame(Progress progress, Results results) {
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
        return results.getResults().stream()
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

        return results.getResults().stream()
            .allMatch(result -> result.isInstanceOf(Gutter.class));
    }

    protected int sumHitPinsCount() {
        return sumHitPinsCount(result -> true);
    }

    public Progress getProgress() {
        return progress;
    }

    public Results getResults() {
        return results;
    }
}
