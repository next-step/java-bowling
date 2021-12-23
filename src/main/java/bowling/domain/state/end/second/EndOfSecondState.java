package bowling.domain.state.end.second;

import bowling.domain.state.end.EndState;
import bowling.domain.state.progress.GeneralProgress;

public abstract class EndOfSecondState implements EndState {

    protected static final String MARK_SEPARATOR = "|";

    protected final GeneralProgress generalProgress;

    protected EndOfSecondState(GeneralProgress generalProgress) {
        this.generalProgress = generalProgress;
    }

    protected String getSecondPrintMark(String marker) {
        return generalProgress.getBeforeProgressPinCount() + MARK_SEPARATOR + marker;
    }
}
