package bowling.domain.progress;

import bowling.domain.state.end.ResultState;

public class ProgressFactory {

    public static Progress create() {
        return new FirstProgress();
    }

    public static Progress progress(ResultState beforeState) {
        return new GeneralProgress(beforeState);
    }

    public static Progress closed() {
        return new Closed();
    }
}
