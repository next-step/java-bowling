package bowling.view;

import bowling.domain.Pitch;

import java.util.Objects;

public class PitchResultHelper {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final int PINS_MIN_COUNT = 0;

    //todo 상태 객체로 만들기..?
    public static String pitchResult(Pitch previousPitch, Pitch pitch) {
        if (!Objects.isNull(previousPitch) && previousPitch.isSecondStrike(pitch)
                || Objects.isNull(previousPitch) && pitch.isStrike()) {
            return STRIKE;
        }
        if (pitch.fallDownPinsSize() == PINS_MIN_COUNT) { // gutter
            return GUTTER;
        }
        if (!Objects.isNull(previousPitch) && previousPitch.isSpare(pitch)) { // spare
            return SPARE;
        }
        return String.valueOf(pitch.fallDownPinsSize());// miss
    }
}
