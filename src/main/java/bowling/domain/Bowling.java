package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Bowling {
    private Frames frames;

    public Bowling(Frames frames) {
        this.frames = frames;
    }

    public void start() {
        Frame frame = NormalFrame.first();
        frames = Frames.of(frames, frame);
        OutputView.printFrame(frames);

        for (int number = 0; number < Frame.LAST_FRAME - 1; number++) {
            frame = processNormalFrame(frames, frame);
            frames = Frames.of(frames, frame);
        }
        processFinalFrame(frames, frame);
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

    private static void processFinalFrame(Frames frames, Frame frame) {
        while (frame.canBowl()) {
            Score score = Score.of(InputView.scanBowl(frame));
            frame.bowl(score);
            OutputView.printFrame(frames);
        }
    }
}
