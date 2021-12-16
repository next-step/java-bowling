package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlFactory;
import bowling.domain.bowl.BowlType;
import bowling.domain.bowl.CanNotPitchException;
import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class FinalFrame implements Frame {

    private static final int MAX_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT_WITH_BONUS = MAX_PITCH_COUNT + 1;
    private static final int FIRST_BOWL_INDEX = 0;
    private static final int INDEX_UNIT = 1;
    private static final int NUMBER = 10;

    private int pitchCount;
    private final List<Bowl> bowls = new ArrayList<>(singletonList(BowlFactory.firstBall()));

    @Override
    public boolean pitch(Pin pin) {
        checkCanPitch();
        pitchCount++;

        Bowl bowl = lastBowl().pitch(pin);
        setLastBowlTo(bowl);

        if (isStrikeOrSpare(bowl) && pitchCount < MAX_PITCH_COUNT_WITH_BONUS) {
            bowls.add(BowlFactory.firstBall());
        }
        return canPitch();
    }

    private void checkCanPitch() {
        if (!canPitch()) {
            throw new CanNotPitchException();
        }
    }

    private boolean canPitch() {
        if (pitchCount < MAX_PITCH_COUNT) {
            return true;
        }
        if (pitchCount > MAX_PITCH_COUNT) {
            return false;
        }
        return isStrikeOrSpare(bowls.get(FIRST_BOWL_INDEX));
    }

    private boolean isStrikeOrSpare(Bowl bowl) {
        return bowl.typeEquals(BowlType.STRIKE) || bowl.typeEquals(BowlType.SPARE);
    }

    private void setLastBowlTo(Bowl bowl) {
        bowls.set(bowls.size() - INDEX_UNIT, bowl);
    }

    private Bowl lastBowl() {
        return bowls.get(bowls.size() - INDEX_UNIT);
    }

    @Override
    public Frame next() {
        throw new NoNextFrameException();
    }

    @Override
    public boolean hasNextFrame() {
        return false;
    }

    @Override
    public int getNumber() {
        return NUMBER;
    }

    @Override
    public List<Bowl> bowls() {
        return unmodifiableList(bowls);
    }
}
