package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.GameInfo;
import bowling.domain.frame.Frame;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameApplication {
    public static void main(String[] args) {
        List<String> players = inputPlayers();

        BowlingGame game = BowlingGame.create(players);
        RandomPitchNumberStrategy numberStrategy = new RandomPitchNumberStrategy();
        while (!game.isGameEnd()) {
            GameInfo gameInfo = game.currentGameInfo();
            Frame frame = gameInfo.run(numberStrategy);

            ResultView.showBoard(game, frame);
            game.changeTurn();
        }
    }

    private static List<String> inputPlayers() {
        List<String> players = new ArrayList<>();
        int playerCount = InputView.inputPlayerCount();

        for (int cound = 0; cound < playerCount; cound++) {
            players.add(InputView.inputPlayerName());
        }

        ResultView.printFrame(players);
        return players;
    }
}
