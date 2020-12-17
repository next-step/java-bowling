package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    public static final int ALL_PINS = 10;
    public static final int FRAME_INIT = 0;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();

        Frame frame = new Frame(FRAME_INIT);
        for (int i = 0; i < 10; i++) {
            Frame afterFirstStroke = frame.bowl(Bowling.stroke(ALL_PINS));
            Frame next = secondStroke(frame, afterFirstStroke);

            frames.add(next);
            frame = next;
        }
    }

    private Frame secondStroke(Frame frame, Frame afterFirstStroke) {
        if (frame.equals(afterFirstStroke)) {
            return frame.bowl(Bowling.stroke(ALL_PINS - afterFirstStroke.getScore().get()));
        }
        return afterFirstStroke;
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
