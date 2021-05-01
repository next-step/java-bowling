package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.Collectors;

public class BowlingGameController {

    public void startGame(InputView inputView, ResultView resultView) {
        NumberOfPlayers numberOfPlayers = new NumberOfPlayers(inputView.receiveNumberOfPlayers());
        Players players = inputView.receivePlayerNames(numberOfPlayers.getValue())
                                   .stream()
                                   .map(Player::initialize)
                                   .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));

        BowlingGame bowlingGame = new BowlingGame(players);
        resultView.printPlayers(bowlingGame.exportPlayers());

        while (!bowlingGame.isFinished()) {
            String currentPlayerName = bowlingGame.getNameOfCurrentPlayer();
            RollResult rollResult = RollResult.of(inputView.receiveNumberOfKnockedDownPins(currentPlayerName));
            bowlingGame.roll(rollResult);

            resultView.printPlayers(bowlingGame.exportPlayers());
        }
    }

}
