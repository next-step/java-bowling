package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    Frame pitch(Pins pins);
    int getIndex();
    boolean hasNext();
    int score();
    int calculateAdditionalScore(Score score);
    List<Bowl> getBowls();
    boolean isEnd();
    Frame getNextFrame();
}
