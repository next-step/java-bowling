package bowling.controller;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class GameController {

  public void run() {

    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    Player player = new Player(inputView.inputName());

    Frame frame = NormalFrame.createFirst();

    FrameResult frameResult = new FrameResult();
    while (!frame.isEnd()) {
      frame.play(new PinCount(inputView.inputPinCount(frame)));
      frameResult.add(frame.getPlayCount(), frame.getState());

      resultView.printResult2(player, frameResult);
      frame = frame.next();
    }
  }
}
