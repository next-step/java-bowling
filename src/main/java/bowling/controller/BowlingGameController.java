package bowling.controller;

import bowling.domain.GameInformation;
import bowling.domain.PinCount;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

  public void run() {

    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    GameInformation gameInformation = new GameInformation(inputView.inputName());
    Frame frame = NormalFrame.createFirst();

    while(!frame.isEnd()) {
      frame.play(new PinCount(inputView.inputPinCount(frame)));

      gameInformation.addFrameResult(frame);

      resultView.printResult2(gameInformation);
      frame = frame.next();

    }

  }


}
