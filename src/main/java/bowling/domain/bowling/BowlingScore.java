package bowling.domain.bowling;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Pitches;

public class BowlingScore {

    private final Pitches pitches;

    public BowlingScore(Pitches pitches) {
        this.pitches = pitches;
    }

    public String getScore() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = pitches.getPitchCounts();
        for (int i = 0; i < size; i++) {
            appendHorizon(stringBuilder);
            stringBuilder.append(parse(i));
        }
        return stringBuilder.toString();
    }

    private void appendHorizon(StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("|");
        }
    }

    private String parse(int index) {
        return index == 0 || index == 2 ? parseEvenDigit(index) : parseOddDigit(index);
    }

    private String parseEvenDigit(int index) {
        if (pitches.isStrike()) {
            return "X";
        }
        Pitch pitch = pitches.getPitches().get(index);
        return pitch.isGutter() ? "-" : String.valueOf(pitch.getHitCounts());
    }

    private String parseOddDigit(int index) {
        Pitch pitchLast = pitches.getPitches().get(index - 1);
        Pitch pitch = pitches.getPitches().get(index);
        if (pitch.getHitCounts() == 10 && pitchLast.getHitCounts() == 10) {
            return "X";
        }
        if (pitch.isGutter()) {
            return "-";
        }
        return pitches.isSpare() ? "/" : String.valueOf(pitch.getHitCounts());
    }
}
