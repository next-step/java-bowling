package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final User user;
    private final List<Frame> frames;

    private Frames(User user) {
        this(user, new ArrayList<>());
    }

    private Frames(User user, List<Frame> frames) {
        this.user = user;
        this.frames = frames;
    }

    public static Frames of(User user) {
        return new Frames(user);
    }
}




