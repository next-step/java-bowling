package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

  public static void main(String[] args) {
    InputView inputView = new InputView();
    String name = inputView.requestName();

    OutputView outputView = new OutputView(name);
    outputView.render();

    int pins = inputView.requestPins(1);
    Bowling bowling = Bowling.first(pins);
    outputView.render(bowling);

    while (bowling.nextFrame() < 11) {
      bowling.roll(inputView.requestPins(bowling.nextFrame()));
      outputView.render(bowling);
    }
  }

}
