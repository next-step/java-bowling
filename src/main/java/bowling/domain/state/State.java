package bowling.domain.state;

import bowling.domain.frame.Point;

public interface State {

    int STRIKE_POINT = 10;
    int GUTTER_POINT = 0;

    State nextScore(Point point);

    String getScore();

    int getPoint();

    boolean isStrike();

    boolean isSpare();
}
