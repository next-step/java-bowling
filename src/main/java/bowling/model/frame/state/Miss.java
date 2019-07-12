package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.Pins.DOWN_ALL;

public class Miss implements State {

    private final Pins firstBowl;
    private final Pins secondBowl;

    private Miss(Pins firstBowl, Pins secondBowl) {
        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
    }

    static State valueOf(Pins firstBowl, Pins secondBowl) {
        if (firstBowl.equals(DOWN_ALL)) {
            throw new IllegalArgumentException(String.format("첫번째 값이 %s보다 작아야합니다", DOWN_ALL));
        }
        return new Miss(firstBowl, secondBowl);
    }

    @Override
    public State bowl(Pins pins) {
        throw new UnsupportedOperationException("더 이상 진행 할 수 없습니다");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(firstBowl.toString(), secondBowl.toString());
    }
}
