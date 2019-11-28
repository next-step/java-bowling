package bowling;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

import static bowling.domain.FrameNumber.LAST_FRAME;

public class BowlingMain {
    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> playerNames = InputView.inputPlayerNames(playerCount);
        Players players = Players.of(playerNames);
        OutputView.printDashBoard(players);

        for (int i = 0; i < LAST_FRAME; i++) {
            playPlayer(players, i);
        }
    }

    private static void playPlayer(Players players, int frameIndex) {
        int playersSize = players.size();
        for (int i = 0; i < playersSize; i++) {
            playFrame(players, players.playerByIndex(i), frameIndex);
        }
    }

    private static void playFrame(Players players, Player player, int index) {
        while (player.isFallDownAble(index)) {
            int pinCount = InputView.inputPinCount(player.getName(), player.frameByIndex(index));
            player.fallDown(index, pinCount);
            OutputView.printDashBoard(players);
        }
    }
}
