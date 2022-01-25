package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.LastDefaultFrame;
import bowling.domain.frame.NormalDefaultFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static int BOARD_MAX_SIZE = 10;

    private final List<Frame> frames = new ArrayList<>();

    public void init() {
        for (int frameNumber = 1; frameNumber < 10; frameNumber++) {
            frames.add(new NormalDefaultFrame(frameNumber));
        }
        frames.add(new LastDefaultFrame());
    }

    public void play(KnockedPins knockedPins) {

    }
    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }


}

