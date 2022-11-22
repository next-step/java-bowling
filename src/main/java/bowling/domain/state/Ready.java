package bowling.domain.state;

import bowling.domain.PinCount;

public class Ready extends Running {

    @Override
    public State next(PinCount pinCount) {
        if (pinCount.isTen()) {
            return new Strike();
        }

        return new FirstBowl(pinCount);
    }
}
