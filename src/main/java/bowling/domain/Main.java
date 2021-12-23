package bowling.domain;

import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Players players = new Players(Arrays.asList(InputView.readPlayerName()));
        OutputView.printBowlingBoard(players);

        while (!players.isEndGame()) {
            playBowlingGames(players);
        }
    }

    private static void playBowlingGames(Players players) {
        for (Player player : players.value()) {
            playBowlingGameAndPrintScore(players, player);
        }
    }

    private static void playBowlingGameAndPrintScore(Players players, Player player) {
        player.prepareFrame();
        while (player.isNotCurrentFrameEnd()) {
            int knockedOutCount = InputView.readKnockedOutCountOf(player.name());
            player.bowl(knockedOutCount);
            OutputView.printBowlingBoard(players);
        }
    }
}
