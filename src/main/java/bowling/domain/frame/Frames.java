package bowling.domain.frame;

import bowling.domain.dto.ScoreData;
import bowling.domain.pin.Pins;
import bowling.domain.dto.StateData;
import bowling.domain.score.Score;
import bowling.exception.NullArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {

    private static final int LIMIT_COUNT_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>(LIMIT_COUNT_OF_FRAMES);
        frames.add(CommonFrame.of());
    }

    public static Frames of() {
        return new Frames();
    }

    public boolean isBowlingFinish() {
        return createFrame().isBowlingFinish();
    }

    public void hitPins(Pins pins) {
        validate(pins);

        Frame frame = createFrame();
        frame.hitPins(pins);
        frame.addFrame(frames);
    }

    public List<StateData> getAllStates() {
        return frames.stream()
                .map(Frame::getFrameStates)
                .collect(Collectors.toList());
    }

    public List<ScoreData> getScores() {
        return frames.stream()
                .map(Frame::getScore)
                .filter(Score::isCompute)
                .map(ScoreData::of)
                .collect(Collectors.toList());
    }

    private Frame createFrame() {
        return frames.get(frames.size() - 1);
    }

    private void validate(Pins pins) {
        if (Objects.isNull(pins)) {
            throw new NullArgumentException("Pin들은");
        }
    }

}
