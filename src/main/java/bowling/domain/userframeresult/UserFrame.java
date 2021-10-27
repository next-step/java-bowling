package bowling.domain.userframeresult;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResults;
import bowling.domain.user.User;

public class UserFrame {

    private final User user;
    private Frame firstFrame;

    private UserFrame(User user, Frame firstFrame) {
        this.user = user;
        this.firstFrame = firstFrame;
    }

    public static UserFrame of(User user, Frame firstFrame) {
        return new UserFrame(user, firstFrame);
    }

    public FrameResults createFrameResults() {
        return FrameResults.createFrameResultsByFirstFrame(firstFrame);
    }

}
