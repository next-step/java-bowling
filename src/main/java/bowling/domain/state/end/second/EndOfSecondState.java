package bowling.domain.state.end.second;

import bowling.domain.state.end.EndState;
import bowling.domain.state.progress.SecondProgress;

public abstract class EndOfSecondState implements EndState {

    protected static final String MARK_SEPARATOR = "|";

    protected final SecondProgress secondProgress;

    protected EndOfSecondState(SecondProgress secondProgress) {
        this.secondProgress = secondProgress;
    }

    protected String getSecondPrintMark(String marker) {
        return secondProgress.getBeforeProgressPinCount() + MARK_SEPARATOR + marker;
    }
}
