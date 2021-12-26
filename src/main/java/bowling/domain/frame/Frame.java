package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.progress.Opened;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.result.StateFactory;
import bowling.domain.result.ResultState;
import bowling.domain.result.status.PinResultState;
import bowling.domain.result.Results;
import bowling.domain.result.status.Gutter;
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

    public boolean isOpened() {
        return this.progress instanceof Opened;
    }

    public boolean isClosed() {
        return !isOpened();
    }

    protected Progress searchNextProgress(Pin pin) {
        if (isClosed()) {
            throw new BowlingProgressException();
        }

        PinResultState resultState = ((Opened) progress).pitch(pin);

        if (isMiss(resultState)) {
            clearAndAddResultMiss(pin);
            return nextProgress(StateFactory.miss(pin));
        }

        results.add(resultState);

        return nextProgress(resultState);
    }

    protected int sumHitPinsCount() {
        return results.sumAll();
    }

    protected int sumHitPinsCount(Predicate<? super ResultState> predicate) {
        return results.sum(predicate);
    }

    protected boolean isMiss(ResultState resultState) {
        if (results.isEmpty()) {
            return false;
        }

        if (!resultState.isInstanceOf(Gutter.class)) {
            return false;
        }

        return results.getResults().stream()
            .allMatch(result -> result.isInstanceOf(Gutter.class));
    }

    private void clearAndAddResultMiss(Pin pin) {
        results.clear();
        results.add(StateFactory.miss(pin));
    }

    protected abstract Progress nextProgress(ResultState resultState);

    protected abstract boolean isNextAbleState(ResultState resultState);

    public abstract Frame bowl(Pin pin);

    public Progress getProgress() {
        return progress;
    }

    public Results getResults() {
        return results;
    }
}
