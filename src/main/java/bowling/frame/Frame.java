package bowling.frame;

import bowling.score.Score;

public interface Frame {

  Frame play(int pinCount);

  String getScoreMessage();

  boolean isGameEnd();

  Score score();

  Score frameScoreAdd(Score score);
}