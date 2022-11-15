package bowling.domain;

import bowling.type.BowlingScore;

public interface Frame {
    boolean isInProgress();

    BowlingScore getBowlingScore();

    int getFirstScore();

    int getSecondScore();

    int getThirdScore();

    void nextTry();

    Frame nextFrame();

    int getLatestScore();

    int getOrder();

    int getScoreSize();
}
