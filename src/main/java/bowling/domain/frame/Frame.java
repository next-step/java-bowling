package bowling.domain.frame;

import bowling.domain.Score;

public interface Frame {

    void setSecondScore(Score secondScore);

    String convert();

    boolean isSpare(Score scoreA, Score scoreB);

    void setThirdScore(Score thirdScore);

    boolean isLastFrame();
}
