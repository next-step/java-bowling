package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameLauncher {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();


    private List<Player> requestPlayers() {

        int playerCount = inputView.requestPlayerCount();
        return IntStream.range(0, playerCount)
            .mapToObj(position -> {
                String name = inputView.requestPlayer(position);
                return new Player(name,position);
            }).collect(Collectors.toList());
    }

    private BowlingGame createGame(int playerCount) {
        return BowlingGame.newInstance(playerCount);
    }

    private void playFrame(List<Player> players, BowlingGame bowlingGame, int framePosition) {
        while (true) {
            List<Integer> playerPositions = bowlingGame.getCurrentPlayers(framePosition);
            if (playerPositions.isEmpty()) {
                break;
            }

            playerPositions.forEach(playerPosition -> {
                playBowl(players.get(playerPosition), bowlingGame);
                outputView.printResult(players, bowlingGame.getResult());
            });
        }
    }

    private void playBowl(Player player, BowlingGame bowlingGame) {
        int downPin = inputView.requestDownPin(player);
        bowlingGame.play(player.getPosition(), downPin);
    }

    public static void main(String[] args) {
        BowlingGameLauncher gameLauncher = new BowlingGameLauncher();

        List<Player> players = gameLauncher.requestPlayers();

        BowlingGame bowlingGame = gameLauncher.createGame(players.size());


        for (int framePosition = 0; framePosition < 10; framePosition++) {
            gameLauncher.playFrame(players, bowlingGame, framePosition);
        }
    }
}
