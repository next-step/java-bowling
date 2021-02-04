package bowling.domain.frame.status;

import bowling.bowlingexception.IllegalFrameRecordException;

public abstract class Ended implements Status {

    @Override
    public Status record(int downedPin) {
        throw new IllegalFrameRecordException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
