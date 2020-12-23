package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Pins;

import java.util.List;

public interface Frame {

    Frame bowl(int count);

    boolean isGameEnd();

    Frame getNextFrame();

    List<Pins> getPinsList();

    Score getScore();

    Score addScore(Score score);
}
