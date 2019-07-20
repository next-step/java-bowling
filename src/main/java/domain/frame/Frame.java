package domain.frame;

import domain.Pins;
import domain.score.BonusType;
import domain.score.Score;
import domain.state.State;

import java.util.List;

public interface Frame {

    Frame setKnockedDownPins(Pins knockedDown);

    Score getScore();

    List<State> getState();

    boolean isClosed();

    Score getBonusScore(Score score);


}
