package bowling.domain.score;

import bowling.domain.point.Point;

public interface Score2 {
    int GUTTER = 0;
    int STRIKE = 10;
    Score2 nextScore(Point point);
    String getScore();
    boolean isStrike();
    boolean isSpare();
    int getPoint();
}
