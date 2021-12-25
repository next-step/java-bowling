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

    public abstract Frame bowl(Pin pin);

    protected abstract Progress nextProgress(ResultState resultState);

    protected abstract boolean isNextAbleState(ResultState resultState);

    public boolean isOpened() {
        return this.progress instanceof Opened;
    }

    protected boolean isClosed() {
        return !isOpened();
    }

    protected Progress searchNextProgress(Pin pin) {
        if (isClosed()) {
            throw new BowlingProgressException();
        }

        ResultState resultState = ((Opened) progress).pitch(pin);

        if (isMiss(resultState)) {
            clearAndAddResultMiss();
            return nextProgress(StateFactory.miss());
        }

        results.add(resultState);

        return nextProgress(resultState);
    }

    private void clearAndAddResultMiss() {
        results.clear();
        results.add(StateFactory.miss());
    }

    protected int sumHitPinsCount(Predicate<? super ResultState> predicate) {
        return results.getResults().stream()
            .filter(result -> result.isInstanceOf(PinResultState.class))
            .filter(predicate)
            .mapToInt(result -> ((PinResultState) result).getHitPinCount())
            .sum();
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
