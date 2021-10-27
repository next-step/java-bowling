package bowling.domain.userframeresult;

import bowling.domain.frame.Frame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.exception.userframeresult.DuplicateUserException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BowlingGameResult {

    private int nowTurnIndex = 0;
    private final List<UserFrameResult> userFrameResults;

    private BowlingGameResult(List<UserFrameResult> userFrameResults) {
        this.userFrameResults = userFrameResults;
    }

    public static BowlingGameResult init() {
        return new BowlingGameResult(new ArrayList<>());
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
        return userFrameResults.get(nowTurnIndex).user().value();
    }

    public void bowlingNowTurnUser(Pin pin) {
        UserFrameResult nowUserFrameResult = userFrameResults.get(nowTurnIndex);
        Frame frame = nowUserFrameResult.lastFrame();
        frame.bowling(pin);
        if (frame.frameIsFinished()) {
            nowTurnIndex++;
            nowTurnIndex %= userFrameResults.size();
        }
        createFrameResult();
    }

    private void createFrameResult() {
        userFrameResults
            .forEach(UserFrameResult::createFrameResults);
    }

    public boolean allBowlingFinished() {
        return userFrameResults.get(userFrameResults.size()-1).isFinished();
    }

    public Stream<UserFrameResult> stream() {
        return userFrameResults.stream();
    }

}
