package bowling;

import bowling.model.BowlingGame;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;

public class BowlingController {

  public static void main(String[] args) {
    BowlingGame bowlingGame = BowlingGame.createWith(BowlingInput.getPlayerNameInput());

    BowlingView.printScoreBoard(bowlingGame.getPlayerName(), bowlingGame.getFramesDTO());

    while (bowlingGame.requiredNormalFrame()) {
      bowlingGame.roll(BowlingInput.getKnockDownNumInput(bowlingGame.getCurrentFrameNum()));
      BowlingView.printScoreBoard(bowlingGame.getPlayerName(), bowlingGame.getFramesDTO());
    }

    bowlingGame.initBonusCnt();

    while (bowlingGame.hasBonus()) {
      bowlingGame.bonusRoll(BowlingInput.getBonusFrameInput());
      BowlingView.printScoreBoardWithBonus(bowlingGame.getPlayerName(), bowlingGame.getFramesDTO());
    }

  }
}
