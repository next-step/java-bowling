package bowling.domain.userframeresult;

import bowling.domain.frame.Frame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.exception.userframeresult.DuplicateUserException;
import java.util.ArrayList;
import java.util.List;

public class UserFrameResults {

    private int nowTurnIndex = 0;
    private final List<UserFrameResult> userFrameResults;

    private UserFrameResults(List<UserFrameResult> userFrameResults) {
        this.userFrameResults = userFrameResults;
    }

    public static UserFrameResults init() {
        return new UserFrameResults(new ArrayList<>());
    }

    public void addUser(User user) {
        checkDuplicateUser(user);

        userFrameResults.add(UserFrameResult.initByUser(user));
    }

    private void checkDuplicateUser(User user) {
        for (UserFrameResult userFrameResult : userFrameResults) {
            checkUserEqual(user, userFrameResult);
        }
    }

    private void checkUserEqual(User user, UserFrameResult userFrameResult) {
        if (userFrameResult.user().equals(user.value())) {
            throw new DuplicateUserException();
        }
    }

    public String nowTurnUser() {
        return userFrameResults.get(nowTurnIndex).user();
    }

    public void bowlingNowTurnUser(Pin pin) {
        UserFrameResult nowUserFrameResult = userFrameResults.get(nowTurnIndex);
        Frame frame = nowUserFrameResult.lastFrame();
        Frame nextFrame = frame.bowling(pin);
        if (frame != nextFrame) {
            nowTurnIndex++;
            nowTurnIndex %= userFrameResults.size();
        }
    }

}
