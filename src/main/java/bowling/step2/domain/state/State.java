package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.List;

public interface State {
    int COUNT_OF_MAX_PINS = 10;
    
    State bowl(int fallenPins);
    
    boolean isFinished();
    
    boolean isSpare();
    
    List<Score> getScores();
    
    boolean isStrike();
}
