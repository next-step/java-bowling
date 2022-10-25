package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {

    private final List<Pin> pins = new ArrayList<>();
    private final Frame frame;

    public Score(Frame frame) {
        this.frame = frame;
    }

    public void addPin(Pin pin) {
        if (frame instanceof NormalFrame && pins().size() == 2) {
            throw new IllegalStateException("더 이상 던질 수 없습니다.");
        }

        int sum = pins.stream()
                .mapToInt(Pin::count)
                .sum();

        if (frame instanceof FinalFrame && sum + pin.count() > 20) {
            throw new IllegalArgumentException("핀 갯수가 올바르지 않습니다.");
        }

        if (frame instanceof NormalFrame && sum + pin.count() > 10) {
            throw new IllegalArgumentException("핀 갯수가 올바르지 않습니다.");
        }

        pins.add(pin);
    }

    public ScoreType status() {
        if (frame instanceof FinalFrame) {
            return getFinalFrameScore();
        }

        return getNormalFrameScore();
    }

    private ScoreType getFinalFrameScore() {
        if (pins.size() == 1 && pins.get(0).count() == 10) {
            return ScoreType.STRIKE;
        }

        if (pins.size() == 2 && (pins.get(0).count() + pins.get(1).count()) == 10) {
            return ScoreType.PROCEEDING;
        }

        if (pins().size() < 2) {
            return ScoreType.PROCEEDING;
        }

        return ScoreType.MISS;
    }

    private ScoreType getNormalFrameScore() {
        if (pins.size() == 1 && pins.get(0).count() == 10) {
            return ScoreType.STRIKE;
        }

        if (pins.size() < 2) {
            return ScoreType.PROCEEDING;
        }

        if (pins.size() == 2 && (pins.get(0).count() + pins.get(1).count()) == 10) {
            return ScoreType.SPARE;
        }

        return ScoreType.MISS;
    }

    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }
}
