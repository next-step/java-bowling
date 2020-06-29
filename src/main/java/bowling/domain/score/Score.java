package bowling.domain.score;

import bowling.domain.frame.Point;

public interface Score {

    int STRIKE_POINT = 10;
    int GUTTER_POINT = 0;

    Score nextScore(Point point);

    String getScore();

    boolean isStrike();

    boolean isSpare();
}
