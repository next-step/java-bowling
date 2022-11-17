package bowling.step4;

import bowling.step4.domain.BowlingGame;
import bowling.step4.domain.Player;
import bowling.step4.view.InputView;
import bowling.step4.view.ResultView;

import java.util.List;

public class BowlingGameMain {

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> names = InputView.inputPlayerName(playerCount);
        BowlingGame bowlingGame = new BowlingGame(names);
        ResultView.printScoreBoard(bowlingGame.createResult());

        for (int i = 1; i <= 10; i++) {
            playFrame(bowlingGame, i);
        }
    }

    private static void playFrame(BowlingGame bowlingGame, int i) {
        for (Player player : bowlingGame.getPlayers()) {
            playPitch(bowlingGame, i, player);
        }

    }

    private static void playPitch(BowlingGame bowlingGame, int i, Player player) {
        while (!player.isEndedFrame(i)) {
            int fallenPinCount = InputView.inputFallenPinCounts(player.name(), i);
            player.bowl(i, fallenPinCount);
            ResultView.printScoreBoard(bowlingGame.createResult());
        }
    }
}
