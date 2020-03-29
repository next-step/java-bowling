package bowling;


import bowling.application.BowlingService;
import bowling.ui.BowlingController;

public class Application {

    public static void main(String[] args) {

        BowlingController bowlingController = new BowlingController(new BowlingService());
        bowlingController.bowl(1);
    }
}
