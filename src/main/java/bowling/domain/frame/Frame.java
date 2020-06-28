package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {

    int NORMAL_MAX_BOWL_PITCH = 2;
    int FINAL_MAX_BOWL_PITCH = 3;

    Frame bowl(Point point);

    boolean isLastPitch();

    List<Score> getScores();
}
