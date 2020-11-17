package bowling.domain.state;

import bowling.domain.point.Point;

public interface State {
    int STRIKE = 10;
    int GUTTER = 0;
    State nextScore(Point point);
    String getScore();
    int getPoint();
    boolean isStrike();
    boolean isSpare();

}
