package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.ArrayList;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    InputView inputView = new InputView();
    ResultView resultView = new ResultView();
    String name = inputView.inputName();
    Player player = new Player(name);

    Frame frame = NormalFrame.createFirst();
    Frames frames = new Frames();

    while(!frame.isEnd()) {
      int pinCount = inputView.inputPinCount(frame);
      frame.play(pinCount);

      frames.add(frame.get());

      resultView.printResult(player, frames);

      frame = frame.next();

    }
  }
}
