package bowling_step4;

import bowling_step4.controller.BowlingGameController;

public class Application {

  public static void main(String[] args) {
    BowlingGameController bowlingGameController = new BowlingGameController();
    bowlingGameController.run();
  }
}
