package bowling;

import bowling.controller.BowlingGameController;

public class Application {

  public static void main(String[] args) {
    BowlingGameController bowlingGameController = new BowlingGameController();
    bowlingGameController.run();
  }
}
