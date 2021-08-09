package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.dto.StateDatas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {

    private static final int LIMIT_SIZE_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>(LIMIT_SIZE_OF_FRAMES);
        frames.add(CommonFrame.of());
    }

    public static Frames of() {
        return new Frames();
    }

    public boolean isBowlingFinish() {
        return createFrame().isBowlingFinish();
    }

    private Frame createFrame() {
        return frames.get(frames.size() - 1);
    }

    public void hitPins(Pins pins) {
        validate(pins);

        Frame frame = createFrame();
        frame.hitPins(pins);
        frame.addFrame(frames);
    }

    private void validate(Pins pins) {
        if (Objects.isNull(pins)) {
            throw new IllegalArgumentException("Pin들은 null일 수 없습니다.");
        }
    }

    public List<StateDatas> getAllStates() {
        return frames.stream()
                .map(FrameResult::getFrameStates)
                .collect(Collectors.toList());
    }

}
