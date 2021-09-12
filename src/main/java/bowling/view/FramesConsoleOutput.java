package bowling.view;

import bowling.dto.FrameStatusDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FramesConsoleOutput {

    private static final String FRAMES_TOP = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_FORMAT = "%-3s";
    private static final String FRAME_DELIMITER = " |  ";
    private static final String PITCH_VALUE_DELIMITER = "|";
    private static final String PITCHES_PREFIX = "|  ";
    private static final String PITCHES_SUFFIX = " |";
    private static final String SCORES_PREFIX = "|      |  ";
    private static final String SCORES_SUFFIX = " |\n";

    public static void print(final String playerName, final FrameStatusDto frameStatusDto) {
        System.out.println(FRAMES_TOP);
        System.out.println(getFramePitches(playerName, frameStatusDto.getAllFramePitchValues()));
        System.out.println(getFrameScores(frameStatusDto.getScores()));
    }

    private static String getFrameScores(final List<Integer> scores) {
        return scores.stream()
                .map(score -> score == 0 ? "" : String.valueOf(score))
                .map(score -> String.format(FRAME_FORMAT, score))
                .collect(Collectors.joining(FRAME_DELIMITER, SCORES_PREFIX, SCORES_SUFFIX));
    }

    private static String getFramePitches(final String playerName, final List<List<Integer>> allFramePitchValues) {
        return Stream.of(Collections.singletonList(playerName), allFramePitchValues(allFramePitchValues))
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .collect(Collectors.joining(FRAME_DELIMITER, PITCHES_PREFIX, PITCHES_SUFFIX));
    }

    private static List<String> allFramePitchValues(final List<List<Integer>> allFramePitchValues) {
        return allFramePitchValues.stream()
                .map(FramesConsoleOutput::convertToSigns)
                .collect(Collectors.toList());
    }

    private static String convertToSigns(final List<Integer> pitchValues) {
        return String.format(FRAME_FORMAT, IntStream.range(0, pitchValues.size())
                .mapToObj(i -> PitchSign.convertToSign(pitchValues, i))
                .collect(Collectors.joining(PITCH_VALUE_DELIMITER)));
    }

}
