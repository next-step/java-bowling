package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.frame.BaseFrame;
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
    Frames frames = new Frames();

    while (!frame.isEnd()) {
      frame.play(inputView.inputPinCount(frame));
      frames.add(frame);
      resultView.printResult(player, frames);
      frame = frame.next();

    }
  }
}
