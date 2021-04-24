package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private InputView inputView;
    private ResultView resultView;

    public BowlingController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        Player player = Player.from(inputView.inputPlayer());
        Frames frames = Frames.newInstance();
        frames.init();
        resultView.printScoreBoard(player.toString(), frames);

        while (frames.isPlayable()) {
            int countOfDownPin = inputView.inputCountOfDownPin(frames.getRound());
            frames.play(countOfDownPin);
            resultView.printScoreBoard(player.toString(), frames);
        }
    }
}
