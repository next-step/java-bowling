package bowling.refactoring.domain.state;

import bowling.refactoring.domain.frame.*;

public interface State {
    boolean isEnd();

    boolean isEndedCalculateScore();

    void calculateBonusScore(Frame nextFrame);

    int totalScore();

}
