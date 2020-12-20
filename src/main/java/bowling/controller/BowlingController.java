package bowling.controller;

import bowling.model.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingController {

    public void run() {
        Players players = new Players();
        int numberOfPlayers = InputView.printInputPlayerCountMessage();

        for (int i = 0; i < numberOfPlayers; i++) {
            String userName = InputView.printInputUserNameMessage(i + 1);
            players.addNewPlayer(userName);
        }

        ResultView.printInfo(players.info());

        while (!players.isGameEnd()) {
            int fallenPins = InputView.printInputFallenPinsMessage(players.nowPlayerName());
            players.bowling(fallenPins);
            ResultView.printInfo(players.info());
        }
    }
}
