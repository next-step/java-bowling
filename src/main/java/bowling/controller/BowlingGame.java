package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGame {
    public static void start() {
        int playerCount = InputView.getPlayerCount();

        List<PlayerName> playerNames = new ArrayList<>();
        IntStream.range(0, playerCount)
                .forEach(count ->
                        playerNames.add(PlayerName.of(InputView.getPlayerName(count + 1))));

        List<GameResult> gameResults = new ArrayList<>();
        playerNames.stream()
                .forEach(playerName -> gameResults.add(GameResult.of(playerName, Frame.createTenFrames())));

        ResultView.print(GameResults.of(gameResults));
    }
}