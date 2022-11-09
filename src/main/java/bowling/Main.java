package bowling;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        int playerCount = InputView.scanPlayerCount();
        Players players = Players.of(InputView.scanPlayerNames(playerCount));
        ResultView.printFrames(players);

        while (!players.isAllFinished()) {
            playBowling(players);
        }

        InputView.closeScan();
    }

    private static void playBowling(Players players) {
        for (Player player: players.getPlayers()) {
            bowlInAFrame(players, player);
        }
    }

    private static void bowlInAFrame(Players players, Player player) {
        bowl(players, player);
        while (!player.isCurrentFrameFinished()) {
            bowl(players, player);
        }
    }

    private static void bowl(Players players, Player player) {
        int fallenPins = InputView.scanFallenPinCount(player.getPlayerName());
        tryBowling(player, fallenPins);
        ResultView.printFrames(players);
    }

    private static void tryBowling(Player player, int fallenPins) {
        try {
            player.bowl(fallenPins);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
