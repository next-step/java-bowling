package bowling.domain;

import bowling.view.ResultView;

public abstract class Frame {
    public static final int FRAME_INIT = 1;

    public static Frame init(Users users) {
        return new NormalFrame(users, FRAME_INIT);
    }

    public abstract Frame bowl(ResultView resultView);

    public abstract Users getUsers();
}
