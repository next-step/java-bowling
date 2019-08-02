package bowling.state;

import bowling.Score;

public class Strike extends Finished {

  private static final String STRIKE_SYMBOL = "X";

  @Override
  public String desc() {
    return STRIKE_SYMBOL;
  }

  @Override
  public Score getScore() {
    return Score.strike();
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    return prevScore.addScore(getScore());
  }

}
