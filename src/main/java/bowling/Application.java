package bowling;

import bowling.controller.BawlingGameController;

public class Application {

  public static void main(String[] args) {
    BawlingGameController bawlingGameController = new BawlingGameController();
    bawlingGameController.run();

  }
}
