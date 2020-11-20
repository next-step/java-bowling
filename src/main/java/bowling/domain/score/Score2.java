package bowling.domain.score;

import bowling.domain.point.Point;

public interface Score2 {
    int STRIKE = 10;
    int GUTTER = 0;
    Score2 nextScore(Point point);
    String getScore();
    int getPoint();
    boolean isStrike();
    boolean isSpare();

}
