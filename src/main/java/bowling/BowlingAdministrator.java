package bowling;

import bowling.frame.Frame;
import bowling.score.Score;

public class BowlingAdministrator {

    public static Frame playBowling(Frame frame, String inputScore) {
        Score score = Score.from(inputScore);
        frame.pitch(score);
        return frame;
    }
}
