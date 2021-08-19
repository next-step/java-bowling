package bowling.state;

import bowling.pin.Pin;

import java.util.List;

public interface State {
    State update(final Pin pin);
    boolean isClean();
    boolean isEnd();
    List<Integer> getScore();
}
