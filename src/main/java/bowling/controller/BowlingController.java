package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Player player = new Player(inputView.playerName());
        playGame(player);
        inputView.close();
    }

    protected void playGame(Player player) {
        resultView.printMark(player.name(), player.marks());

        while (!player.hasFinishedGame()) {
            String pinCount = inputView.pinCount(player.currentFrameIndex());
            player.throwBowl(pinCount);
            resultView.printMark(player.name(), player.marks());
            resultView.printScore(player.scores());
        }
    }
}
