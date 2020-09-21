package bowling.controller;

import bowling.model.frame.Frames;
import bowling.model.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import static bowling.util.ValidationUtils.tryUntilSuccess;

public class BowlingGame {
    InputView inputView;
    ResultView resultView;

    public BowlingGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        Player player = tryUntilSuccess(this::getPlayer, resultView::printError);
        Frames frames = new Frames();
        while(!frames.isEnded()) {
            tryUntilSuccess(() -> addResult(frames), resultView::printError);
            resultView.printHeader();
            resultView.printFrames(player, frames.getDto());
        }
    }

    private Frames addResult(Frames frames) {
        int currentIndex = frames.getCurrentFrame().getIndex();
        int count = inputView.inputFramePinCount(currentIndex);
        frames.addResult(count);
        return frames;
    }

    private Player getPlayer() {
        String name = inputView.inputPlayerName();
        return new Player(name);
    }
}
