package bowling.step3;

import bowling.step3.domain.Player;
import bowling.step3.view.InputView;
import bowling.step3.view.ResultView;

public class BowlingGameMain {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Player player = new Player(name);
        ResultView.printScoreBoard(player);

        for (int i = 1; i <= 10; i++) {
            playFrame(player, i);
        }
    }

    private static void playFrame(Player player, int i) {
        while (!player.IsEndedFrame(i)) {
            int fallenPinCount = InputView.inputFallenPinCounts(i);
            player.bowl(fallenPinCount);
            ResultView.printScoreBoard(player);
        }
    }
}
