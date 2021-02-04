package bowling.domain.frame.status;

import bowling.domain.score.Score;

public interface Status {

    String getDescription();

    Status record(int downedPin);

    boolean isEnd();

    Score calculateBaseScoreOfFrame();

    Score addBonus(Score originalScore);
}
