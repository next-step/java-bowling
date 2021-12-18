package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class Frames {

    private static final int INDEX_UNIT = 1;

    public final List<Frame> frames;

    public Frames(Frame firstFrame) {
        this(new ArrayList<>(singletonList(firstFrame)));
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        Frame firstFrame = Frame.firstOf(new FirstBowl());
        return new Frames(firstFrame);
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        if (currentFrame().pitch(pin)) {
            return true;
        }
        if (frames.size() == Frame.MAX_FRAME_NUMBER) {
            return false;
        }
        return frames.add(createNextFrame());
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - INDEX_UNIT);
    }

    private Frame createNextFrame() {
        Frame currentFrame = currentFrame();
        if (isNormalFrameOrder()) {
            return currentFrame.nextOf(new FirstBowl());
        }
        return currentFrame.nextOf(new FinalBowl());
    }

    private boolean isNormalFrameOrder() {
        return frames.size() < Frame.MAX_FRAME_NUMBER - INDEX_UNIT;
    }

    public int numberOfFrame() {
        return frames.size();
    }

    public List<Bowl> bowls() {
        return frames.stream()
                .map(Frame::getBowl)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
