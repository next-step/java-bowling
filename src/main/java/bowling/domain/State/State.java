package bowling.domain.State;

import bowling.domain.Score;

public interface State {
    int MAX_PINS = 10;
    String STRIKE = "X";
    String DELIMITER = "|";
    String STANDBY = "";

    State bowl(int felledPins);

    boolean isFinish();

    String getDesc();

    Score getScore();

    Score calculateAdditionalScore(Score score);
}
