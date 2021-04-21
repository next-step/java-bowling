package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameController {
    public void run(){

        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        String playerName = inputView.inputName();
        BowlingGame game = new BowlingGame(playerName);

        resultView.printEmptyResult(playerName);

        while (!game.isEnd()) {
            int count = inputView.inputPinCount(game.getFrameCount());
            game.play(count);
            resultView.printResult(playerName, game.getFrames().getFrames(), game.getScore());
        }
    }

    public void run2() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        int playerCount= inputView.inputPlayerCount();

        List<String> playerNames = IntStream.range(0, playerCount)
            .mapToObj(index -> inputView.inputName())
            .collect(Collectors.toList());

        BowlingGames bowlingGames = new BowlingGames(playerNames);

        resultView.printResult(bowlingGames);



    }
}
