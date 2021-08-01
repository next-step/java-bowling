package bowling.domain.frame;

import bowling.dto.StateDtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int LIMIT_SIZE_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>(LIMIT_SIZE_OF_FRAMES);
        frames.add(GeneralFrame.init());
    }

    public static Frames init() {
        return new Frames();
    }

    public List<StateDtos> getTotalStates() {
        return frames.stream()
                .map(Frame::getFrameStates)
                .collect(Collectors.toList());
    }

    public boolean isBowlingEnd() {
        return false;
    }
}
