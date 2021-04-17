package bowling.controller;

import bowling.domain.GameInformation;
import bowling.domain.PinCount;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

  public void run() {

    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    GameInformation gameInformation = new GameInformation(inputView.inputName());
    Frame frame = new NormalFrame(1, new Ready());

    while(!frame.isEnd()) {
      frame.play(new PinCount(inputView.inputPinCount(frame)));

      State state = frame.getState();
      int playCount = frame.getPlayCount();

      gameInformation.addFrameResult(playCount,state);

      resultView.printResult2(gameInformation);

      frame = frame.nextFrame();

    }
  }


}
