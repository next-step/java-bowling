package bowling;

import bowling.controller.BowlingGamesController;
import bowling.exception.CannotBowlException;
import bowling.view.InputView;

public class Main {
  public static void main(String[] args) throws CannotBowlException {
    InputView inputView = new InputView();

    new BowlingGamesController(inputView).play();
  }
}
