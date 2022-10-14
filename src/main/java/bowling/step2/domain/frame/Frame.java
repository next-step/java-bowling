package bowling.step2.domain.frame;

import bowling.step2.domain.Score;

import java.util.List;

public interface Frame {
    int READY_SCORE = -1;
    
    Frame bowl(int fallenPins);
    
    boolean isNormalFrame();
    
    List<Score> getScores();
    
    int getOneNextScore();
    
    int getTwoNextScore();
}
