package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.score.Score;

public interface Frame {

    boolean isFrameEnd();

    void pitch(Pins pins);

    String getSymbol();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

}
