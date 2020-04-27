package bowling;

import bowling.domain.BowlingGames;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        int playerCount = InputView.inputPlayerCount();
        Players players = new Players();
        for (int i = 0; i < playerCount; i++) {
            String name = InputView.inputPlayerName().trim();
            players = players.addPlayer(new Player(name));
        }

        BowlingGames games = BowlingGames.from(players);

        int frameNumber = 1;

        while (!games.isAllEnd()) {
            frameNumber = playGamePlayers(games, players, frameNumber);
        }
    }

    private static int playGamePlayers(final BowlingGames games,
                                       final Players players,
                                       int frameNumber) {
        for (Player player : players.getPlayers()) {
            frameNumber = playGamePlayer(games, player, frameNumber);
        }
        return frameNumber;
    }

    private static int playGamePlayer(final BowlingGames games,
                                      final Player player,
                                      int frameNumber) {

        while (games.isMoreThrowable(player, frameNumber)) {
            int inputBowlCount =
                    Integer.parseInt(InputView.inputBowlCount(player, frameNumber));
            games.playPlayerGame(player, inputBowlCount);

            ResultView.printScoreBoardTop();
            ResultView.printAllPlayerBoard(games, frameNumber);
        }

        if (games.isAllPlayerLastFrameFinish(frameNumber)) {
            frameNumber++;
        }

        return frameNumber;
    }
}
