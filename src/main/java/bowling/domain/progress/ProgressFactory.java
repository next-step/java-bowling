package bowling.domain.progress;

import bowling.domain.state.end.EndState;

public class ProgressFactory {

    public static Progress create() {
        return new FirstProgress();
    }

    public static Progress progress(EndState beforeState) {
        return new GeneralProgress(beforeState);
    }

    public static Progress closed() {
        return new Closed();
    }
}
