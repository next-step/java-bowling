package bowling.step4.dto;

import bowling.step4.domain.Pitch;
import bowling.step4.domain.Pitches;

import java.util.List;

public class PinCountDto {
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";


    public final String count;

    public PinCountDto(String count) {
        this.count = count;
    }

    public static PinCountDto from(Pitches pitches) {
        int pitchCounts = pitches.value().size();
        if (pitchCounts == 0) {
            return new PinCountDto("");
        }

        String result = "";

        if (pitchCounts == 1) {
            result += getText(pitches.value().get(0).count());
        }

        if (pitchCounts == 2) {
            result += getTextInCaseOfTwo(pitches.value());
        }

        if (pitchCounts == 3) {
            result += getTextInCaseOfThree(pitches.value());
        }
        return new PinCountDto(result);
    }

    private static String getTextInCaseOfThree(List<Pitch> pitches) {

        if (pitches.get(0).count() != 10 && pitches.get(0).count() + pitches.get(1).count() == 10) {
            return String.format("%s|/|%s", getText(pitches.get(0).count()), getText(pitches.get(2).count()));
        }

        if (pitches.get(1).count() + pitches.get(2).count() == 10) {
            return String.format("%s|%s|/", getText(pitches.get(0).count()), getText(pitches.get(1).count()));
        }

        return String.format("%s|%s|%s", getText(pitches.get(0).count()), getText(pitches.get(1).count()), getText(pitches.get(2).count()));
    }

    private static String getTextInCaseOfTwo(List<Pitch> pitches) {
        if (pitches.get(0).count() != 10 && pitches.get(0).count() + pitches.get(1).count() == 10) {
            return String.format("%s|/", getText(pitches.get(0).count()));
        }
        return String.format("%s|%s", getText(pitches.get(0).count()), getText(pitches.get(1).count()));
    }

    private static String getText(int count) {
        if (count == 10) {
            return STRIKE_TEXT;
        }
        if (count == 0) {
            return ZERO_TEXT;
        }
        return String.valueOf(count);
    }
}
