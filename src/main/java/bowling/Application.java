package bowling;

import bowling.domain.FrameResult;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;


public class Application {

  public static void main(String[] args) {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();

    Player player = new Player(inputView.inputName());

    Frame frame = NormalFrame.createFirst();
    FrameResult frameResult = new FrameResult();

    while (!frame.isEnd()) {
      frame.play(inputView.inputPinCount(frame));
      frameResult.add(frame);
      resultView.printResult(player, frameResult);
      frame = frame.next();

    }
  }
}
