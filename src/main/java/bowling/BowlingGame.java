package bowling;


import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        ResultView.startBowlingGame(InputView.players());
    }
}

