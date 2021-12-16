package bowling.domain;

import bowling.strategy.PitchNumberStrategy;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Frame> frames = new ArrayList<>();

    private BowlingGame() {
    }

    public static BowlingGame init() {
        return new BowlingGame();
    }

    public void run(Player player, PitchNumberStrategy numberStrategy) {
        Frame frame = NormalFrame.first();
        while (progressing()) {
            validateFrameCreate(frame);
            frame = run(frame, numberStrategy);
        }
        ResultView.showBoard(player, frames);
    }

    private Frame run(Frame frame, PitchNumberStrategy numberStrategy) {
        frame.run(numberStrategy);
        frames.add(frame);
        if (!progressing()) {
            return null;
        }
        return frame.next();
    }

    private boolean progressing() {
        return frames.size() < 10;
    }

    private void validateFrameCreate(Frame frame) {
        assert frame != null;
    }
}
