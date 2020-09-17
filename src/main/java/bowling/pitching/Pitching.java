package bowling.pitching;

import bowling.global.exception.InputPitchPointNullPointerException;
import bowling.global.exception.OutOfPitchRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;
import org.apache.logging.log4j.util.Strings;

public class Pitching {

    private static final int PITCHING_MIN_NUMBER = 0;
    public static final int PITCHING_MAX_NUMBER = 10;
    private static final int FIRST_PITCHING_COUNT = 1;
    private static final int PITCHING_INCREASE_NUMBER = 1;

    private int pitchingPoint;
    private int pitchingCount;

    private Pitching(String pitchingPoint, int pitchingCount) {
        validateInputPitchPointIsNull(pitchingPoint);
        this.pitchingPoint = new StringParser(pitchingPoint).toInt();
        validateOutofPitchRange();
        this.pitchingCount = pitchingCount;
    }

    public static Pitching pitch(String pitchPoint, int pitchCount) {
        return new Pitching(pitchPoint, pitchCount);
    }

    private void validateInputPitchPointIsNull(String pitchPoint) {
        if (Strings.isBlank(pitchPoint)) {
            throw new InputPitchPointNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofPitchRange() {
        if (pitchingPoint < PITCHING_MIN_NUMBER || pitchingPoint > PITCHING_MAX_NUMBER) {
            throw new OutOfPitchRangeException(ExceptionMessage.INVALID_PITCH_RANGE);
        }
    }

    public int getPitchingPoint() {
        return pitchingPoint;
    }

    public int getPitchingCount() {
        return pitchingCount;
    }

    public boolean isFirstPitchingCount() {
        return pitchingCount == FIRST_PITCHING_COUNT;
    }

    public boolean canPitch(int pitchingCountLimit) {
        return pitchingCount <= pitchingCountLimit;
    }

    public int increasePitchingCount() {
        return pitchingCount += PITCHING_INCREASE_NUMBER;
    }

    @Override
    public String toString() {
        return "Pitching{" +
                "pitchingPoint=" + pitchingPoint +
                ", pitchingCount=" + pitchingCount +
                '}';
    }
}
