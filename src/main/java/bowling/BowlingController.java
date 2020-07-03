package bowling;

import bowling.model.BowlingGame;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;

public class BowlingController {

  public static void main(String[] args) {
    BowlingGame bowlingGame = BowlingGame.createWith(BowlingInput.getPlayerNameInput());

    BowlingView.printScoreBoard(bowlingGame.getPlayerName(), bowlingGame.getFramesDTO());
    BowlingView.printScores(bowlingGame.getScores());

    while (bowlingGame.requiredNormalFrame()) {
      bowlingGame.roll(BowlingInput.getKnockDownNumberInput(bowlingGame.getCurrentFrameNumber()));
      BowlingView.printScoreBoard(bowlingGame.getPlayerName(), bowlingGame.getFramesDTO());
      BowlingView.printScores(bowlingGame.getScores());
    }
  }
}
