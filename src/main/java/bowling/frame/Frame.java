package bowling.frame;

import bowling.bowl.Bowl;
import bowling.pin.Pins;
import bowling.score.Score;

public interface Frame {
    Frame pitch(Pins pins);
    int getIndex();
    boolean hasNext();
    String getSymbol();
    int score();
    int calculateAdditionalScore(Score score);
}
