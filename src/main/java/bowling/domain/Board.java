package bowling.domain;

import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.LastDefaultFrame;
import bowling.domain.frame.NormalDefaultFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    public static int BOARD_MAX_SIZE = 10;

    private final List<DefaultFrame> defaultFrames = new ArrayList<>();

    public void init() {
        for (int index = 0; index < 9; index++) {
            defaultFrames.add(new NormalDefaultFrame());
        }
        defaultFrames.add(new LastDefaultFrame());
    }

    public List<DefaultFrame> getFrames() {
        return Collections.unmodifiableList(defaultFrames);
    }

    public DefaultFrame getRecentFrame(int index) {
        return defaultFrames.get(index);
    }
}

