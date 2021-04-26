package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public interface Frame {

  boolean isEnd();

  Frame next();

  Score getScore();

  Frame play(Pin pinCount);

  Score calculateAdditionalScore(Score score);

  int getPlayCount();
}
