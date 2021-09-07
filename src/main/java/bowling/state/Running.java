package bowling.state;

import bowling.exception.ScoreCalculateException;
import bowling.score.Score;

public abstract class Running implements State {

  private static final String MSG_ERROR_NOT_FINISH = "아직 종료되지 않은 상태입니다.";

  @Override
  public boolean isFinish() {
    return false;
  }

  @Override
  public Score score() {
    throw new ScoreCalculateException(MSG_ERROR_NOT_FINISH);
  }
}