package bowling.domain.userframeresult;

import bowling.domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class UserFrameResults {

    private final List<UserFrameResult> userFrameResults;

    private UserFrameResults(List<UserFrameResult> userFrameResults) {
        this.userFrameResults = userFrameResults;
    }

    public static UserFrameResults init() {
        return new UserFrameResults(new ArrayList<>());
    }

    public void addUser(User user) {
        userFrameResults.add(UserFrameResult.initByUser(user));
    }

}
