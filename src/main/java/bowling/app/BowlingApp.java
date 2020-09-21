package bowling.app;

import bowling.view.InputView;

public class BowlingApp {

    public static void main(String[] args) {
        int playerCount = InputView.getPlayerCount();
        BowlingExecutor.execute(playerCount);
    }

}
