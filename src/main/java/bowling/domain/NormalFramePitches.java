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

    @Override
    public boolean add(final Pitch pitch) {
        if (isFull()) {
            return false;
        }
        requireValidSum(pitch);
        return pitches.add(pitch);
    }

    @Override
    public boolean isFull() {
        return isStrike(FIRST_INDEX)
                || pitches.size() == PITCHES_LIMIT;
    }

    @Override
    public Score score(final Frame nextFrame) {
        if (!isFull()) {
            return Score.noScore();
        }
        if (isStrike(FIRST_INDEX)) {
            return getTotalScoreIfBonusExist(nextTwoFramePitchValues(nextFrame), STRIKE_BONUS_PITCH_COUNT);
        }
        if (isSpare()) {
            return getTotalScoreIfBonusExist(nextPitchValues(nextFrame), SPARE_BONUS_PITCH_COUNT);
        }
        return Score.from(sum());
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

    private Score getTotalScoreIfBonusExist(final List<Integer> bonusPitches, final int limit) {
        if (bonusPitches.size() < limit) {
            return Score.noScore();
        }
        return Score.from(sum()
                + bonusPitches.stream()
                .limit(limit)
                .reduce(0, Integer::sum));
    }

    private void requireValidSum(final Pitch pitch) {
        if (sum(pitch) > TOTAL_MAX) {
            throw new NormalFrameInvalidPitchesSumException(TOTAL_MAX);
        }
    }
}
