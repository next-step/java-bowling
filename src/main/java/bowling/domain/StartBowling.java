package bowling.domain;

import bowling.domain.state.Strike;

public class StartBowling {
    private final User user;
    private final NormalFrame frame;

    public StartBowling(User user) {
        this.user = user;
        this.frame = new NormalFrame();
    }

    public void play(Pin pin) {
    }


}
