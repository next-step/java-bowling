package bowling.domain;

import bowling.exception.NormalFrameInvalidPitchesSumException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NormalFrame extends Frame {

    private static final int FIRST_INDEX = 0;
    private static final int PITCHES_LIMIT = 2;
    private static final int TOTAL_MAX = 10;
    private static final int STRIKE_BONUS_PITCH_COUNT = 2;
    private static final int SPARE_BONUS_PITCH_COUNT = 1;
    private static final int NO_SCORE = 0;

    @Override
    public boolean addPitchIfPossible(final Pitch pitch) {
        if (isFull()) {
            return false;
        }
        requireValidSum(pitch);
        return pitches.add(pitch);
    }

    @Override
    public boolean isFull() {
        return isStrike()
                || pitches.size() == PITCHES_LIMIT;
    }

    @Override
    public int score() {
        if (!isFull()) {
            return NO_SCORE;
        }
        if (isStrike()) {
            return getTotalScoreIfBonusExist(nextTwoFramePitchValues(), STRIKE_BONUS_PITCH_COUNT);
        }
        if (isSpare()) {
            return getTotalScoreIfBonusExist(nextPitchValues(), SPARE_BONUS_PITCH_COUNT);
        }
        return pitchesSum();
    }

    private List<Integer> nextTwoFramePitchValues() {
        return Stream.of(nextPitchValues(), nextAfterNextPitchValues())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> nextPitchValues() {
        return Optional.ofNullable(nextFrame)
                .map(Frame::pitchValues)
                .orElse(Collections.emptyList());
    }

    private List<Integer> nextAfterNextPitchValues() {
        return Optional.ofNullable(nextFrame)
                .map(nextFrame -> nextFrame.nextFrame)
                .map(Frame::pitchValues)
                .orElse(Collections.emptyList());
    }

    private int getTotalScoreIfBonusExist(final List<Integer> bonusPitches, final int limit) {
        if (bonusPitches.size() < limit) {
            return NO_SCORE;
        }
        return pitchesSum() + bonusPitches.stream()
                .limit(limit)
                .reduce(NO_SCORE, Integer::sum);
    }

    private void requireValidSum(final Pitch pitch) {
        if (pitchesSum(pitch) > TOTAL_MAX) {
            throw new NormalFrameInvalidPitchesSumException(TOTAL_MAX);
        }
    }

    private boolean isSpare() {
        return pitches.size() == PITCHES_LIMIT
                && pitchesSum() == TOTAL_MAX;
    }

    private boolean isStrike() {
        return !pitches.isEmpty()
                && firstPitch().isStrike();
    }

    private Pitch firstPitch() {
        return pitches.get(FIRST_INDEX);
    }

}
