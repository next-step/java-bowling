package bowling.domain.score;

import bowling.domain.point.Point;

public interface Score {

    int STRIKE_POINT = 10;
    int GUTTER_POINT = 0;

    Score nextScore(Point point);

    ScoreType getScore();

    Point getPoint();

    boolean isStrike();

    boolean isSpare();
}
