package bowling.entity.score;

import bowling.entity.Score;

public interface ScoreType {

    String scoreResult();

    boolean isFrameEnd();

    Score score();

    Score calculate(Score beforeScore);

    Score frameScore();
}
