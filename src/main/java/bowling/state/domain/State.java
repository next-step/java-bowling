package bowling.state.domain;


import bowling.frame.domain.Score;
import bowling.pin.domain.Pin;

public interface State {

    State roll(Pin felled);

    boolean isEnd();

    String view();

    Score getScore();

}
