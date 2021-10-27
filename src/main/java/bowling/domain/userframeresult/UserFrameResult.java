package bowling.domain.userframeresult;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResults;
import bowling.domain.score.Pin;
import bowling.domain.user.User;

public class UserFrameResult {

    private final User user;
    private final Frame firstFrame;
    private FrameResults frameResults;

    private UserFrameResult(User user, Frame firstFrame, FrameResults frameResults) {
        this.user = user;
        this.firstFrame = firstFrame;
        this.frameResults = frameResults;
    }

    public static UserFrameResult fromUserAndFirstFrame(User user, Frame firstFrame) {
        return new UserFrameResult(user, firstFrame, FrameResults.createFrameResultsByFirstFrame(firstFrame));
    }

    public boolean isFinished() {
        return firstFrame.lastFrame().isFinished();
    }

    public void bowling(Pin pin) {
        firstFrame.lastFrame().bowling(pin);
    }

    public FrameResults createFrameResults() {
        return frameResults = FrameResults.createFrameResultsByFirstFrame(firstFrame);
    }

}
