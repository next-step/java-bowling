package bowling.frame.state;

import bowling.score.Pin;

import java.util.List;

public abstract class State {

    public abstract State bowl(Pin fellPins);

    public abstract List<String> getBowlResults();

    public abstract boolean isFinish();

}
