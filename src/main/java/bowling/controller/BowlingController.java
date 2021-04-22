package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Name;
import bowling.domain.Player;
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
    }

    private void playGame(Name name, Frames frames) {
        resultView.printResult(name, frames.list());
        Frame frame = frames.first();

        while (!frames.isAllFinished()) {
            int currentIndex = frame.index();
            String pinCount = inputView.PinCount(currentIndex);
            frame = frame.throwBowl(pinCount);
            frames.update(frame);
            frame = frame.next();
            resultView.printResult(name, frames.list());
        }
    }
}
