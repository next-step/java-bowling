package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

  public void run() {
    Player player = new Player(InputView.inputOfPlayerName());
    Frames frames = Frames.init();
    ResultView.showScoreBoard(frames, player);

    while (!frames.isContinue()) {
      frames.throwBall(InputView.inputCountOfHitPin(frames));
      ResultView.showScoreBoard(frames, player);
    }
  }
}
