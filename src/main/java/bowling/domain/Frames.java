package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    public static final int ALL_PINS = 10;
    public static final int FRAME_INIT = 1;
    public static final int ALL_FRAMES = 10;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();

        Frame frame = new Frame(FRAME_INIT);
        while (frame.getFrameNo() <= ALL_FRAMES) {
            Frame afterFirstStroke = firstStroke(frame);
            Frame next = secondStroke(frame, afterFirstStroke);

            frames.add(next);
            frame = next;
        }
    }

    private Frame firstStroke(Frame frame) {
        return frame.bowl(Bowling.stroke(ALL_PINS));
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
