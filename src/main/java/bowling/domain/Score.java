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

        if (frame instanceof FinalFrame && sum + pin.count() > 30) {
            throw new IllegalArgumentException("핀 갯수가 올바르지 않습니다.");
        }

        if (frame instanceof NormalFrame && sum + pin.count() > 10) {
            throw new IllegalArgumentException("핀 갯수가 올바르지 않습니다.");
        }

        pins.add(pin);
    }

    public ScoreType status() {
        return getFrameScore();
    }

    private ScoreType getFrameScore() {
        if (pins.size() == 1 && pinNumber(0) == 10) {
            return ScoreType.STRIKE;
        }

        if (pins.size() == 2 && pinNumber(1) == 10) {
            return ScoreType.FINAL_STRIKE;
        }

        if (pins.size() == 2 && (pinNumber(0) + pinNumber(1)) == 10) {
            return ScoreType.SPARE;
        }

        if (pins.size() == 2 && pinNumber(0) == 10 && pinNumber(1) != 10) {
            return ScoreType.FINAL_SPARE;
        }

        if (pins().size() < 2) {
            return ScoreType.PROCEEDING;
        }

        return ScoreType.MISS;
    }

    public List<Pin> pins() {
        return Collections.unmodifiableList(pins);
    }

    public boolean match(ScoreType scoreType) {
        return status().equals(scoreType);
    }

    public int pinNumber(int index) {
        return pins.get(index).count();
    }
}
