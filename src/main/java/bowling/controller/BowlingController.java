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
        Frames frames = new Frames();
        playGame(player.name(), frames);
        inputView.close();
    }

    private void playGame(Name name, Frames frames) {
        resultView.printMark(name, frames.marks());

        while (!frames.isAllFinished()) {
            String pinCount = inputView.pinCount(frames.currentIndex());
            frames.throwBowl(pinCount);
            resultView.printMark(name, frames.marks());
            resultView.printScore(frames.scores());
        }
    }
}
