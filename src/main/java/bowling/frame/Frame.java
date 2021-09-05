package bowling.frame;

import bowling.score.Score;
import bowling.score.ScoreBoard;
import bowling.score.ScoreResult;

public interface Frame {

  Frame play(int pinCount);

  String getScoreMessage();

  boolean isGameEnd();

  Score score();

  Score frameScoreAdd(Score score);

  int scoreValue();

  ScoreResult createScoreResult();

  ScoreBoard createScoreBoard();

  void addScoreResult(ScoreBoard scoreBoard);
}