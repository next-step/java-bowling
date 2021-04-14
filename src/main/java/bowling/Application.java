package bowling;

import bowling.domain.Frames;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Application {

  public static void main(String[] args) {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    String name = inputView.inputName();

    Frame frame = NormalFrame.createFirst();
    Frames frames = new Frames();

    while(!frame.isEnd()) {
      int pinCount = inputView.inputPinCount(frame);
      frame.play(pinCount);

      frame = frame.next();

    }
  }
}
