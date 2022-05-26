package bowling.bowl;

import bowling.score.Score;
import bowling.pin.Pins;

public interface Bowl {

    boolean isEnd();

    Bowl pitch(Pins pins);

    String getSymbol();

    Score score();

    Score calculateScore(Score before);
}
