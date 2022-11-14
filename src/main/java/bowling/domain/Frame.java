package bowling.domain;

import bowling.type.BowlingScore;

public interface Frame {
    boolean isProgress();
    BowlingScore getBowlingScore();
    int getFirstScore();
    int getSecondScore();
    int getThirdScore();
    void nextTry();
    Frame nextRound();
    int getLatestScore();
    int getOrder();
    int getScoreSize();
}
