package bowling.view;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Pitches;

public class BowlingScoreParser {
    private static final int LOOP_ZERO = 0;
    private static final int STRING_BUILDER_LENGTH_ZERO = 0;

    private BowlingScoreParser() {
    }

    public static String parseScore(Pitches pitches) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = pitches.getPitchCounts();
        for (int i = LOOP_ZERO; i < size; i++) {
            appendVerticalLine(stringBuilder);
            stringBuilder.append(parsePitches(i, pitches));
        }
        return stringBuilder.toString();
    }

    private static void appendVerticalLine(StringBuilder stringBuilder) {
        if (stringBuilder.length() > STRING_BUILDER_LENGTH_ZERO) {
            stringBuilder.append(ViewMessages.VERTICAL_LINE);
        }
    }

    private static String parsePitches(int index, Pitches pitches) {
        return index == 0 || index == 2 ? parseEvenDigitPitch(index, pitches) : parseOddDigitPitch(index, pitches);
    }

    private static String parseEvenDigitPitch(int index, Pitches pitches) {
        if (pitches.isStrike()) {
            return ViewMessages.STRIKE;
        }
        Pitch pitch = pitches.getPitchByIndex(index);
        return pitch.isGutter() ? ViewMessages.GUTTER : String.valueOf(pitch.getHitCounts());
    }

    private static String parseOddDigitPitch(int index, Pitches pitches) {
        Pitch pitch = pitches.getPitchByIndex(index);
        if (pitches.isStrike() && pitch.getHitCounts() == 10) {
            return "X";
        }
        if (pitch.isGutter()) {
            return "-";
        }
        return pitches.isSpare() ? ViewMessages.SPARE : String.valueOf(pitch.getHitCounts());
    }
}
