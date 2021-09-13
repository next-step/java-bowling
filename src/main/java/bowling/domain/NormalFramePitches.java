package bowling.domain;

import bowling.exception.NormalFrameInvalidPitchesSumException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NormalFramePitches extends Pitches {

    private static final int FIRST_INDEX = 0;
    private static final int PITCHES_LIMIT = 2;
    private static final int TOTAL_MAX = 10;
    private static final int STRIKE_BONUS_PITCH_COUNT = 2;
    private static final int SPARE_BONUS_PITCH_COUNT = 1;
    private static final int NO_SCORE = 0;

    public boolean add(final Pitch pitch) {
        if (isFull()) {
            return false;
        }
        requireValidSum(pitch);
        return pitches.add(pitch);
    }

    public boolean isFull() {
        return isStrike(FIRST_INDEX)
                || pitches.size() == PITCHES_LIMIT;
    }

    public int score(final Frame nextFrame) {
        if (!isFull()) {
            return NO_SCORE;
        }
        if (isStrike(FIRST_INDEX)) {
            return getTotalScoreIfBonusExist(nextTwoFramePitchValues(nextFrame), STRIKE_BONUS_PITCH_COUNT);
        }
        if (isSpare()) {
            return getTotalScoreIfBonusExist(nextPitchValues(nextFrame), SPARE_BONUS_PITCH_COUNT);
        }
        return sum();
    }

    private List<Integer> nextTwoFramePitchValues(final Frame nextFrame) {
        return Stream.of(nextPitchValues(nextFrame), nextAfterNextPitchValues(nextFrame))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> nextPitchValues(final Frame nextFrame) {
        return Optional.ofNullable(nextFrame)
                .map(Frame::pitchValues)
                .orElse(Collections.emptyList());
    }

    private List<Integer> nextAfterNextPitchValues(final Frame nextFrame) {
        return Optional.ofNullable(nextFrame)
                .map(next -> next.nextFrame)
                .map(Frame::pitchValues)
                .orElse(Collections.emptyList());
    }

    private int getTotalScoreIfBonusExist(final List<Integer> bonusPitches, final int limit) {
        if (bonusPitches.size() < limit) {
            return NO_SCORE;
        }
        return sum() + bonusPitches.stream()
                .limit(limit)
                .reduce(NO_SCORE, Integer::sum);
    }

    private void requireValidSum(final Pitch pitch) {
        if (sum(pitch) > TOTAL_MAX) {
            throw new NormalFrameInvalidPitchesSumException(TOTAL_MAX);
        }
    }
}
