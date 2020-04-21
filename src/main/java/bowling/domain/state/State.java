package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(Score score);

    boolean isFinish();

    int getScore();

    boolean isEnableCalculate();

    int getFirstScore();

    int getSecondeScore();

    boolean isStrike();

    boolean isSpare();

    int spareBonusScore();

    int strikeBonusScore();

    int doubleStrikeBonusScore();
}
