package bowling;

import bowling.controller.BowlingController;

public class Client {

    public static void main(String[] args) {
        BowlingController controller = new BowlingController();
        controller.run();
    }
}
