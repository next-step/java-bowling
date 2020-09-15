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
        for (int number = 1; number < Frame.LAST_FRAME; number++) {
            frames = Frames.of(frames, frame);
            frame = processNormalFrame(frames, frame);
        }
        processFinalFrame(frames, frame);
    }

    private static void processFinalFrame(Frames frames, Frame frame) {
    }

    private static Frame processNormalFrame(Frames frames, Frame frame) {
        OutputView.printFrame(frames);
        Score score = Score.of(InputView.scanFirstBowl(frame));
        frame.bowl(score);

        if (frame.canBowl()) {
            score = Score.of(InputView.scanFirstBowl(frame));;
            frame.bowl(score);
        }
        return frame.next();
    }

}
