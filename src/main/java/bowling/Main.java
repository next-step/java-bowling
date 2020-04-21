package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.exception.CannotBowlException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
  public static void main(String[] args) throws CannotBowlException {
    InputView inputView = new InputView();

    Player player = inputView.acceptPlayer();
    BowlingGame bowlingGame = new BowlingGame(player);
    Frame frame = bowlingGame.getFrame();

    while (!frame.isEnd()) {
      int pinCount = inputView.inputPinCount(frame);
      frame = frame.roll(pinCount);
      ResultView.printBowlingScoreTable(bowlingGame);
    }
  }
}
