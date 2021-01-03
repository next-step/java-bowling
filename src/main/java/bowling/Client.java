package bowling;

import bowling.controller.BowlingGameController;

public class Client {

    public static void main(String[] args) {
        BowlingGameController controller = new BowlingGameController();
        controller.run();
    }
}
