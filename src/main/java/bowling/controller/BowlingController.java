package bowling.controller;

import bowling.BowlingGame;
import bowling.domain.NumberOfPlayer;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.LinkedList;


public class BowlingController {
    private NumberOfPlayer numberOfPlayer;
    private LinkedList<Player> playerList = new LinkedList<>();
    private BowlingGame bowlingGame;

    public void run() {
        numberOfPlayer = new NumberOfPlayer(InputView.requestNumberOfPeople());

        for(int i = 1; i <= numberOfPlayer.number(); i++) {
            playerList.add(new Player(InputView.requestPlayerName(i)));
        }

        bowlingGame = new BowlingGame(playerList);
        ResultView.printBoard(bowlingGame.exportPlayerDTOList());

        while(!bowlingGame.isFinished()) {
            Pins pitch = Pins.of(InputView.requestFallingPins(bowlingGame.currentPlayer().name()));
            bowlingGame.bowl(pitch);
            ResultView.printBoard(bowlingGame.exportPlayerDTOList());
        }
    }
}
