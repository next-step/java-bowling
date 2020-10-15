package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingMain {
    public static void main(String[] args) {
        int playerNumber = InputView.inputPlayerNumber();

        List<String> playerNames = IntStream.range(0, playerNumber)
                .mapToObj(it -> InputView.inputPlayerName())
                .collect(Collectors.toList());

        BowlingGames bowlingGames = new BowlingGames(playerNames);
        OutputView.printScore(bowlingGames);

        while (!bowlingGames.isEnd()) {
            for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
                int count = InputView.inputFallCount(bowlingGame.getPlayer());
                bowlingGame.pitch(count);
                OutputView.printScore(bowlingGames);
            }
        }
    }
}
