package bowling.domain;

import bowling.domain.frame.BowlingFrames;
import bowling.domain.state.FrameBowlStates;
import java.util.List;

public class BowlingGame {

    private final BowlingFrames bowlingFrames;

    private BowlingGame(BowlingFrames bowlingFrames) {
        this.bowlingFrames = bowlingFrames;
    }

    public static BowlingGame newInstance() {
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        return new BowlingGame(bowlingFrames);
    }

    public void play(int numberOfDownPin) {
        this.bowlingFrames.play(numberOfDownPin);
    }

    public int getFramePosition() {
        return this.bowlingFrames.getCurrentPosition();
    }

    public List<FrameBowlStates> getResult() {
        return this.bowlingFrames.getFrameStates();
    }

    public boolean isFinished() {
        return this.bowlingFrames.isFinished();
    }
}
