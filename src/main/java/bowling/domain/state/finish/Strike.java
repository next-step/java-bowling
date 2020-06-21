package bowling.domain.state.finish;

import bowling.domain.state.StateExpression;

public class Strike extends Finished {

    private Strike() {
    }

    public static Strike of() {
        return new Strike();
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public String getDesc() {
        return StateExpression.STRIKE;
    }
}
