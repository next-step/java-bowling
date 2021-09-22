package bowling.domain.pins;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.score.Score;
import java.util.ArrayList;
import java.util.List;

public class Pins {

    public static final Score MAX_DOWNED_PIN_SCORE = Score.from(10);

    private final List<Boolean> pins;
    private Status status;

    private Pins(List<Boolean> pins, Status status) {
        this.pins = pins;
        this.status = status;
    }

    public static Pins create() {
        return new Pins(new ArrayList<>(), Status.READY);
    }

    public static Pins from(Pins pins) {
        return new Pins(new ArrayList<>(pins.pins), pins.status);
    }

    public static Pins of(Pins pins, Status status) {
        return new Pins(new ArrayList<>(pins.pins), status);
    }

    public Score numberOfPinDowns() {
        return Score.from(pins.size());
    }

    public boolean isAllDown() {
        return Status.isEnd(status);
    }

    public Pins roll(Score givenDown, FrameInfo frameInfo) {
        Score standPinsCount = numberOfPinDowns();
        Score totalScore = standPinsCount.sumWithScores(givenDown);

        if (totalScore.compareTo(MAX_DOWNED_PIN_SCORE) > 0) {
            throw new IllegalArgumentException("이미 모든 핀을 다 쓰려뜨렸습니다.");
        }

        pins.clear();

        for (int i = 0; i < givenDown.score(); i++) {
            pins.add(true);
        }

        status = findStatus(standPinsCount, givenDown, frameInfo);

        return Pins.from(this);
    }

    private Status findStatus(Score standPinsCount, Score givenDown, FrameInfo frameInfo) {
        if (givenDown.equals(MAX_DOWNED_PIN_SCORE)) {
            return Status.find(givenDown, false);
        }

        if (isSpare(standPinsCount, givenDown, frameInfo)) {
            return Status.find(MAX_DOWNED_PIN_SCORE, true);
        }

        return Status.find(givenDown, false);
    }

    private boolean isSpare(Score standPinsCount, Score givenDown, FrameInfo frameInfo) {
        Score score = Score.create();
        Score sum = score.sumWithScores(givenDown, standPinsCount);
        return frameInfo.isLastRound() && sum.equals(MAX_DOWNED_PIN_SCORE);
    }

    public Status status() {
        return status;
    }

    public boolean isReady() {
        return Status.READY == status;
    }

    public boolean isStrike() {
        return this.status == Status.STRIKE;
    }

    public boolean isSpare() {
        return this.status == Status.SPARE;
    }
}
