package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.ScoreType;
import bowling.entity.score.ScoreTypeEnum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NormalFrameInfo implements FrameInfo {
    private static final int MAX_NORMAL_FRAME = 9;

    private final int frameNo;
    private final LinkedList<Pin> pins;

    public NormalFrameInfo(int frameNo) {
        this.frameNo = frameNo;
        this.pins = new LinkedList<>();
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        pins.add(fallenPin);

        return getScoreType();
    }

    private ScoreType getScoreType() {
        Pin sumPin = new Pin(pins.stream().mapToInt(Pin::pin).sum());
        return ScoreTypeEnum.findScoreType(pins.size(), sumPin.isTenPin()).createScoreType(pins);
    }

    @Override
    public String scoreResult() {
        return getScoreType().scoreResult();
    }

    public boolean isFrameEnd() {
        return getScoreType().isFrameEnd();
    }

    public List<ScoreType> scoreTypes() {
        List<ScoreType> scoreTypes = new ArrayList<>();
        Pin sumPin = new Pin(0);

        for (int i = 0; i < pins.size(); i++) {
            Pin pin = pins.get(i);
            sumPin = new Pin(sumPin.sum(pin));
            scoreTypes.add(ScoreTypeEnum.findScoreType(i + 1, sumPin.isTenPin()).createScoreType(pins));
        }

        return scoreTypes;
    }

    public boolean nextFrameIsLastFrame() {
        return frameNo == MAX_NORMAL_FRAME;
    }

    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameInfo that = (NormalFrameInfo) o;
        return frameNo == that.frameNo && Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, pins);
    }
}
