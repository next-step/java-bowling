package bowling;

import bowling.frame.Frame;
import bowling.score.Score;
import bowling.view.InputView;

public class BowlingAdministrator {

    public static Frame normalFramePitchi(Frame frame, int frameNumber) {
        if (frame.canNormalPitching()) {
            frame = BowlingAdministrator.playBowling(InputView.inputScore(frameNumber), frame);
        }
        return frame;
    }

    public static Frame playBowling(String inputScore, Frame frame) {
        Score score = Score.from(inputScore);
        frame.pitch(score);
        return frame;
    }
}
