package bowling;

import bowling.domain.*;
import bowling.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {
    private Frames frames;

    public BowlingController(Frames frames) {
        this.frames = frames;
    }

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        Player player = Player.from(name);

        Frames frames = Frames.init(player);
        BowlingController bowlingController = new BowlingController(frames);

        bowlingController.start();
    }

    private void start() {
        Frame frame = NormalFrame.first();
        frames = Frames.of(frames, frame);
        OutputView.printFrame(frames);

        for (int number = 0; number < Frame.LAST_FRAME - 1; number++) {
            frame = processNormalFrame(frames, frame);
            frames = Frames.of(frames, frame);
        }
        processFinalFrame(frames, frame);
    }

    private static void processFinalFrame(Frames frames, Frame frame) {
        while (frame.canBowl()) {
            Score score = Score.of(InputView.scanBowl(frame));
            frame.bowl(score);
            OutputView.printFrame(frames);
        }
    }

    private static Frame processNormalFrame(Frames frames, Frame frame) {
        Score score = Score.of(InputView.scanBowl(frame));
        frame.bowl(score);
        OutputView.printFrame(frames);

        if (frame.canBowl()) {
            score = Score.of(InputView.scanBowl(frame));
            frame.bowl(score);
            OutputView.printFrame(frames);
        }
        return frame.next();
    }

}
