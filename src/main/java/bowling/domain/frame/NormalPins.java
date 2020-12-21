package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalPins implements Pins {

    private static final int MIN_PIN_COUNT = 0;
    private static final int PIN_COUNT = 10;
    private static final int MAX_ROUND = 2;

    private static final int STRIKE_PIN_COUNT = 10;
    private static final int FIRST_BOWL_INDEX = 0;
    private static final int FIRST_ROLL = 1;
    private static final int SECOND_ROLL = 2;
    private static final int NINE_FRAME_NUMBER = 8;
    private static final int FINAL_FRAME_NUMBER = 9;

    private final List<Integer> downPins = new ArrayList<>();

    @Override
    public void down(int downPin) {
        validate(downPin);
        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        return sum() != PIN_COUNT;
    }

    @Override
    public ScoreType getScoreType() {
        if (isStrike()) {
            return ScoreType.STRIKE;
        }

        if (isSpare()) {
            return ScoreType.SPARE;
        }

        if (!hasTurn()) {
            return ScoreType.MISS;
        }

        return ScoreType.READY;
    }

    @Override
    public List<Integer> getDownPins() {
        return new ArrayList<>(this.downPins);
    }

    @Override
    public Score frameScore(FrameNumber frameNumber, Scores scores) {

        if (isLastFrame(frameNumber) || hasTurn()) {
            return Score.create(getDownPins(), ScoreType.READY);
        }

        int bonusBowlCount = getScoreType().getBonusBowlCount();

        List<Integer> nextFrameDownPins = scores.getDownPinsFromIndex(frameNumber.increment());

        if (isStrike()) {
            strikeResolve(frameNumber, scores);
        }

        int nextFrameDownPinsSize = nextFrameDownPins.size();
        if (nextFrameDownPinsSize >= bonusBowlCount) {
            nextFrameDownPins.subList(0, bonusBowlCount);
        }

        if (nextFrameDownPinsSize < bonusBowlCount) {
            return Score.create(getDownPins(), ScoreType.READY);
        }

        int score = sum() + nextFrameDownPins.stream()
            .reduce(0, Integer::sum);
        return Score.create(score, ScoreType.NORMAL);
    }

    private List<Integer> strikeResolve(FrameNumber frameNumber, Scores scores) {
        List<Integer> nextFrameDownPins = scores.getDownPinsFromIndex(frameNumber.increment());

        if (isNextFrameStrike(nextFrameDownPins) && isNotNineFrame(frameNumber)) {
            List<Integer> twoNextFrameDownPins = scores.getDownPinsFromIndex(frameNumber.increment().increment());
            nextFrameDownPins.addAll(twoNextFrameDownPins);
            return nextFrameDownPins;
        }

        return nextFrameDownPins;
    }

    private boolean isNextFrameStrike(List<Integer> nextFrameDownPins) {
        return nextFrameDownPins.size() == FIRST_ROLL
            && nextFrameDownPins.stream().reduce(0, Integer::sum) == 10;
    }

    private boolean isNotNineFrame(FrameNumber frameNumber) {
        return frameNumber.getValue() != NINE_FRAME_NUMBER;
    }

    private boolean isLastFrame(FrameNumber frameNumber) {
        return Objects.equals(frameNumber.getValue(), FINAL_FRAME_NUMBER);
    }

    @Override
    public int sum() {
        return this.downPins
            .stream()
            .reduce(0, Integer::sum);
    }

    private boolean isSpare() {
        return this.downPins.size() == SECOND_ROLL && sum() == STRIKE_PIN_COUNT;
    }

    private boolean isStrike() {
        return this.downPins.size() == FIRST_ROLL
            && this.downPins.get(FIRST_BOWL_INDEX) == STRIKE_PIN_COUNT;
    }

    private void validate(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("이미 다 던졌습니다.");
        }

        if (downPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 0개 보다 더 쓰러질 수 없습니다.");
        }

        if (sum() + downPin > PIN_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 핀 갯수");
        }
    }
}
