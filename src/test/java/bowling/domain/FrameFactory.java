package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameFactory {
    public static final Frame FINAL;
    public static final List<Frame> FRAME_LIST;

    static {
        FRAME_LIST = new ArrayList<>();
        Frame frame = Frame.first();
        FRAME_LIST.add(frame);
        for (int i = 1; i < 10; i++) {
            frame = frame.next();
            FRAME_LIST.add(frame);
        }
        FINAL = FRAME_LIST.get(FRAME_LIST.size() - 1);
    }
}
