package bowling;

import bowling.controller.BowlingController;

public final class BowlingApplication {

    private BowlingApplication() {}

    public static void main(String[] args) {
        new BowlingController().run();
    }
}
