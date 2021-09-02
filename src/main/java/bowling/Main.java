package bowling;

import bowling.frame.FramesResult;
import bowling.player.PlayerName;
import bowling.view.InsertView;
import bowling.view.ResultView;

public class Main {

  public static void main(String[] args) {

    FramesResult result = FramesResult.of(new PlayerName(InsertView.inputName()));
    ResultView.printBoard(result.getResult());

    while (!result.isEndOfGame()) {
      result.playing(InsertView.throwBall(result.round()));
      ResultView.printBoard(result.getResult());
    }
  }
}