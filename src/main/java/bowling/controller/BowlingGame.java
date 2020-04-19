package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {
    private final Frames frames;

    public BowlingGame() {
        this.frames = Frames.create();
    }

    public void play(final InputView inputView) {
        Player player = inputView.inputPlayerName();
        OutputView.printOverHead(player.getName(), frames.getStates(), frames.getScores());
        while (!isEnd()) {
            BowlCount bowlCount = inputView.inputBowlCount(getFrameNumber());
            Pins pins = Pins.of();
            frames.bowl(pins.knockOver(bowlCount));
            OutputView.printOverHead(player.getName(), frames.getStates(), frames.getScores());
        }
    }

    public boolean isEnd() {
        Frame current = frames.getCurrent();
        return current.getFrameNumber().isFinal() && current.isEnd();
    }

    public FrameNumber getFrameNumber() {
        return frames.getCurrent().getFrameNumber();
    }
}
