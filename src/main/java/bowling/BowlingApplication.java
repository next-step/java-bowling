package bowling;

import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        String playerName = InputView.askPlayerName();
        ResultView.printPlayerName(playerName);

        Player player = new Player(playerName);

        for(int i = 1; i <= 10; i++) {
            playBowling(player, i);
        }
    }

    private static void playBowling(Player player, int frameNo) {
        while(!player.isNthFrameOver(frameNo)) {
            player.bowl(InputView.askNumberOfPins(frameNo));
            ResultView.print(player);
        }
    }
}
