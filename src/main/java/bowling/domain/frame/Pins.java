package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import java.util.List;

public interface Pins {

    void down(int downPin);

    boolean hasTurn();

    ScoreType getScoreType();

    List<Integer> getDownPins();

    Score frameScore(FrameNumber frameNumber, Scores scores);

    int sum();
}
