package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.exception.CannotAddPinsException;
import bowling.exception.EndedFrameException;

public abstract class Frame {

    protected static final int ATTEMPTS_TO_BOWL = 2;
    protected static final int ATTEMPTS_TO_BOWL_WITH_BONUS = 3;
    protected static final int MAX_PINS_COUNT = 10;
    private static final int INDEX_OF_1ST_RESULT = 1;
    private static final int INDEX_OF_2ND_RESULT = 2;

    protected final int number;
    protected final List<PitchResult> results;
    private Frame next;

    protected Frame(int number) {
        this.number = number;
        this.results = new ArrayList<>();
    }

    public Frame next() {
        next = createNextFrame();
        return next;
    }

    private Frame createNextFrame() {
        if (number + 1 == LastFrame.FRAME_NUMBER) {
            return new LastFrame();
        }
        return NormalFrame.of(number + 1);
    }

    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new EndedFrameException();
        }

        if (isTotalPinsMoreThan10With(fallenPins)) {
            throw new CannotAddPinsException(totalPins(), fallenPins);
        }

        if (isSpare(fallenPins)) {
            results.add(PitchResult.spare(fallenPins));
            return;
        }

        results.add(PitchResult.of(fallenPins));
    }

    public boolean isEnd() {
        return hasStrike() || results.size() == ATTEMPTS_TO_BOWL;
    }

    public boolean isLast() {
        return number == LastFrame.FRAME_NUMBER;
    }

    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    public int number() {
        return number;
    }

    public Score score() {
        if (!isEnd()) {
            return Score.ofNotScored();
        }

        Score score = Score.from(totalPins());
        if (isLast()) {
            return score;
        }
        return score.add(bonusScore());
    }

    private Score bonusScore() {
        if (hasStrike()) {
            return strikeBonus();
        }
        if (hasSpare()) {
            return spareBonus();
        }
        return Score.ofZero();
    }

    private Score strikeBonus() {
        PitchResult next1stResult = next.firstResult();
        PitchResult next2ndResult = next.secondResult();
        Score next1stScore = Score.of(next1stResult);
        if (next1stResult.isStrike() && number < NormalFrame.MAX_FRAME_NUMBER) {
            Frame nextAfterFrame = next.next;
            PitchResult nextAfter1stResult = nextAfterFrame.firstResult();
            return next1stScore.add(Score.of(nextAfter1stResult));
        }
        return next1stScore.add(Score.of(next2ndResult));
    }

    private Score spareBonus() {
        PitchResult nextResult = next.firstResult();
        return Score.of(nextResult);
    }

    public PitchResult firstResult() {
        return nthResult(INDEX_OF_1ST_RESULT);
    }

    public PitchResult secondResult() {
        return nthResult(INDEX_OF_2ND_RESULT);
    }

    private PitchResult nthResult(int resultIndex) {
        try {
            return results.get(resultIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            return PitchResult.of(Score.NOT_SCORED);
        }
    }

    protected boolean isTotalPinsMoreThan10With(int fallenPins) {
        return totalPins() + fallenPins > MAX_PINS_COUNT;
    }

    protected boolean isSpare(int fallenPins) {
        return !results.isEmpty() && (totalPins() + fallenPins == MAX_PINS_COUNT);
    }

    protected boolean hasStrike() {
        return results.contains(PitchResult.of(MAX_PINS_COUNT));
    }

    protected boolean hasSpare() {
        return results.stream()
                .anyMatch(PitchResult::isSpare);
    }

    protected int totalPins() {
        return results.stream()
                .mapToInt(PitchResult::fallenPins)
                .sum();
    }
}
