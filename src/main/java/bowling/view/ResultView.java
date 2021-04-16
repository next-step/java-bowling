package bowling.view;

import bowling.dto.PlayResult;
import bowling.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static final String FRAME_SEPARATOR = "|";

    public static final String EMPTY_SCORE = "";

    private static final int FIXED_FRAME_SPACE = 6;

    private static final String HEADER_PLAYER_NAME_REPRESENTATION = "NAME";

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printFrameStates(PlayResult playResult) {
        String playerName = playerNameInString(playResult.playerName());
        String framesInString = framesInString(playResult.allStates());

        System.out.print(FRAME_SEPARATOR + playerName + framesInString + FRAME_SEPARATOR);
        printEmptyLine();
    }

    private static String playerNameInString(String name) {
        return StringUtils.center(name, FIXED_FRAME_SPACE) + FRAME_SEPARATOR;
    }

    private static String framesInString(List<String> states) {
        return states.stream()
                .map(state -> StringUtils.center(state, FIXED_FRAME_SPACE))
                .collect(Collectors.joining(FRAME_SEPARATOR));
    }

    public static void printScoreBoardHeader(int totalNumberOfFrame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_SEPARATOR);
        stringBuilder.append(StringUtils.center(HEADER_PLAYER_NAME_REPRESENTATION, FIXED_FRAME_SPACE));

        for (int i = 1; i <= totalNumberOfFrame; i++) {
            stringBuilder.append(FRAME_SEPARATOR);
            String twoDigitFrameNumber = String.format("%02d", i);
            String formattedFrameNumber = StringUtils.center(twoDigitFrameNumber, FIXED_FRAME_SPACE);
            stringBuilder.append(formattedFrameNumber);
        }
        stringBuilder.append(FRAME_SEPARATOR);

        System.out.print(stringBuilder.toString());
        printEmptyLine();
    }

    public static void printScoreHeader() {
        System.out.print(FRAME_SEPARATOR + StringUtils.center(EMPTY_SCORE, FIXED_FRAME_SPACE) + FRAME_SEPARATOR);
    }

    public static void printScore(int accumulatesScore) {
        System.out.print(StringUtils.center(String.valueOf(accumulatesScore), FIXED_FRAME_SPACE) + FRAME_SEPARATOR);
    }

    public static void printEmptyScore() {
        System.out.print(StringUtils.center(EMPTY_SCORE, FIXED_FRAME_SPACE) + FRAME_SEPARATOR);
    }
}
