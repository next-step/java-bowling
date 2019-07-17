package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.Pin.DOWN_ALL;

public class Miss extends SecondState {

    private Miss(Pin firstBowl, Pin secondBowl) {
        super(firstBowl, secondBowl);
    }

    static State valueOf(Pin firstBowl, Pin secondBowl) {
        if (DOWN_ALL.equals(firstBowl) || DOWN_ALL.equals(secondBowl)) {
            throw new IllegalArgumentException("핀을 모두 쓰러트릴 수 없습니다");
        }
        return new Miss(firstBowl, secondBowl);
    }

    @Override
    public Score getScore() {
        Pin totalPin = getFirstBowl().sum(getSecondBowl());
        return Score.parse(totalPin);
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(getFirstBowl().toString(), getSecondBowl().toString());
    }
}