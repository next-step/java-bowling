package bowling.domain;

import bowling.type.BowlingScore;

public interface Frame {
    boolean isProgress();
    boolean isStrike();
    BowlingScore getBowlingScore();
    int getFirstScore();
    int getSecondScore();
    void nextTry();
    Frame nextRound();
    int getLatestScore();
    int getOrder();
}
