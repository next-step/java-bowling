package bowling.controller;

import bowling.domain.BawlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BawlingGameController {

  public void run() {
/*
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

 */
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    Player player = new Player(inputView.inputName());
    BawlingGame bawlingGame = new BawlingGame(player);
    resultView.printResult(player, bawlingGame);

    while(!bawlingGame.isEnd()) {

      int index = bawlingGame.getFrameCount();

      int pinCount = inputView.inputPinCount(index);
      bawlingGame.play(pinCount);

      resultView.printResult(player, bawlingGame);

    }


  }


}
