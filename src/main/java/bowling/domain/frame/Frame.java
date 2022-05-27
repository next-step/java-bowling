package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public interface Frame {
    Frame pitch(Pins pins);
    int getIndex();
    boolean hasNext();
    String getSymbol();
    int score();
    int calculateAdditionalScore(Score score);
}
