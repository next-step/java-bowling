package bowling.view;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FramesConsoleOutput {

    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final String FRAMES_TOP = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String FRAME_DELIMITER = " |  ";
    private static final String PITCH_VALUE_DELIMITER = "|";
    private static final String PREFIX = "|  ";
    private static final String SUFFIX = " |\n";

    public static void print(final String playerName, final List<List<Integer>> allFramePitchValues) {
        System.out.println(getFrames(playerName, allFramePitchValues));
    }

    private static String getFrames(final String playerName, final List<List<Integer>> allFramePitchValues) {
        return Stream.of(Collections.singletonList(playerName), allFramePitchValues(allFramePitchValues))
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .collect(Collectors.joining(FRAME_DELIMITER, FRAMES_TOP + PREFIX, SUFFIX));
    }

    private static List<String> allFramePitchValues(final List<List<Integer>> allFramePitchValues) {
        return allFramePitchValues.stream()
                .map(FramesConsoleOutput::convertToSigns)
                .collect(Collectors.toList());
    }

    private static String convertToSigns(final List<Integer> pitchValues) {
        return String.format("%-3s", IntStream.range(0, pitchValues.size())
                .mapToObj(i -> convertToSign(pitchValues, i))
                .collect(Collectors.joining(PITCH_VALUE_DELIMITER)));
    }

    private static String convertToSign(final List<Integer> pitchValues, final int i) {
        int pitchValue = pitchValues.get(i);
        if (isGutter(pitchValue)) {
            return GUTTER;
        }
        if (isSpare(pitchValues, i)) {
            return SPARE;
        }
        if (isStrike(pitchValue)) {
            return STRIKE;
        }
        return String.valueOf(pitchValue);
    }

    private static boolean isGutter(final int pitchValue) {
        return pitchValue == MIN;
    }

    private static boolean isSpare(final List<Integer> pitchValues, final int i) {
        if (i == 0) {
            return false;
        }
        return pitchValues.get(i - 1) + pitchValues.get(i) == MAX;
    }

    private static boolean isStrike(final int pitchValue) {
        return pitchValue == MAX;
    }

}
