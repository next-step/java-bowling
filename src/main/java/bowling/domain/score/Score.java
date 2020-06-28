package bowling.domain.score;

import bowling.domain.point.Point;

public interface Score {

    Score nextScore(Point point);

    ScoreType getScore();
}
