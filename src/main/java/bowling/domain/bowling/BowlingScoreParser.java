package bowling.domain.bowling;

import bowling.domain.pitch.Pitches;

public class BowlingScoreParser {
    private static final int ZERO = 0;
    private static final int FIRST_PITCH = 1;
    private static final int TEN = 10;
    private static final String VERTICAL_LINE = "|";

    private BowlingScoreParser() {
    }

    public static String parse(Pitches pitches) {
        int pitchCounts = pitches.getPitchCounts();
        return pitchCounts == FIRST_PITCH ? parseOneDigit(pitches) + "  " : parseTwoDigits(pitches);
    }

    private static String parseOneDigit(Pitches pitches) {
        if (pitches.isStrike()) {
            return "X";
        }
        int hitCounts = pitches.getPitches().get(0).getHitCounts();
        return hitCounts == 0 ? "-" : String.valueOf(hitCounts);
    }

    private static String parseTwoDigits(Pitches pitches) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parseOneDigit(pitches))
                .append(VERTICAL_LINE);
        stringBuilder.append(parseSecondDigit(pitches));
        return stringBuilder.toString();
    }

    private static String parseSecondDigit(Pitches pitches) {
        int firstPitch = pitches.getPitches().get(0).getHitCounts();
        int secondPitch = pitches.getPitches().get(1).getHitCounts();
        if (firstPitch + secondPitch == TEN) {
            return "/";
        }
        if (secondPitch == ZERO) {
            return "-";
        }
        return String.valueOf(secondPitch);
    }
}
