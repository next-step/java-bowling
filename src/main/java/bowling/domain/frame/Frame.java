package bowling.domain.frame;

import bowling.domain.Pins;
import qna.domain.Score;

public interface Frame {
    Frame bowl(Pins pins);

    int getIndex();

    boolean isEnd();

    String symbol();

    int score();

    int scoreAfter(Score prevScore);
}
