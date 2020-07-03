package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameController {

    private List<Player> requestPlayers() {

        int playerCount = InputView.inputtPlayerCount();
        return IntStream.range(0, playerCount)
                .mapToObj(InputView::inputPlayer)
                .collect(Collectors.toList());
    }

    private BowlingGame createGame(int playerCount) {
        return BowlingGame.start(playerCount);
    }

    private void playFrame(List<Player> players, BowlingGame bowlingGame, int framePosition) {
        while (true) {
            if (bowlingGame.isMatchFinished()) {
                break;
            }

            List<Integer> playerPositions = bowlingGame.getCurrentPlayers(framePosition);
            playerPositions.forEach(playerPosition -> {
                playBowl(players.get(playerPosition), bowlingGame);
                ResultView.printResult(players, bowlingGame.getResult());
            });
        }
    }

    private void playBowl(Player player, BowlingGame bowlingGame) {
        int downPin = InputView.inputFramePoint(player);
        bowlingGame.roll(player.getPosition(), downPin);
    }

    public void startBowling() {

        List<Player> players = requestPlayers();

        BowlingGame bowlingGame = createGame(players.size());

        for (int framePosition = 0; framePosition < 10; framePosition++) {
            playFrame(players, bowlingGame, framePosition);
        }
    }

}
