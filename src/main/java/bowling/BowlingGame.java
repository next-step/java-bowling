package bowling;


import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void main(String[] args) {

        Players players = InputView.players();

        ResultView.startBowlingGame(players, Frames.listOf(players.countOfPlayers()));
    }
}

