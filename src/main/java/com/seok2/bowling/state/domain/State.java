package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.Score;
import com.seok2.bowling.pin.domain.Pin;

public interface State {

    State roll(Pin felled);

    boolean isEnd();

    Score getScore();

    String view();

    Score calculate(Score base);

    Score getScore();
}
