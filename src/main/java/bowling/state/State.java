package bowling.state;

import bowling.pin.Pin;

import java.util.List;

public interface State {
    State nextPitch(final Pin downedPins);
    boolean isClean();
    boolean isEnd();
    List<Integer> getScore();
}
