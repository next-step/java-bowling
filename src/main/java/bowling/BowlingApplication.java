package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.NormalFrame;
import bowling.view.InputView;

public class BowlingApplication {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();

        Frames frames = Frames.start();
        Frame frame = NormalFrame.first();
        Frames currentFrames = Frames.of(frames, frame);

        while (!currentFrames.isNormalFinish()) {
            int frameNumber = currentFrames.getFrameNumber();
            frame = BowlingAdministrator.playBowling(InputView.inputScore(frameNumber), frame);

            frame = BowlingAdministrator.normalFramePitchi(frame, frameNumber);
            frame = frame.next();
            currentFrames = Frames.of(frames, frame);
        }

        while (frame.canFinalPitching()) {
            frame = BowlingAdministrator.playBowling(InputView.inputScore(frame.getFrameNumber()), frame);
        }

    }
}
