package bowling.view;

import bowling.dto.*;
import bowling.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static final String FRAME_SEPARATOR = "|";

    public static final String EMPTY_SCORE = "";

    private static final int FIXED_FRAME_SPACE = 6;

    private static final String HEADER_PLAYER_NAME_REPRESENTATION = "NAME";

    public static void printScoreBoard(ScoreBoard scoreBoard) {
        printScoreBoardHeader(scoreBoard.totalNumberOfFrame());
        printScoreBoardBody(scoreBoard.playResult());
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private static void printScoreBoardBody(PlayResult playResult) {
        FrameResults frameResults = playResult.framesResult();
        String playerName = playerNameInString(playResult.playerName());
        String framesInString = framesInString(frameResults.allStates());

        System.out.print(FRAME_SEPARATOR + playerName + framesInString + FRAME_SEPARATOR);
        printEmptyLine();
        System.out.print(scoresInString(frameResults.allScores()));
        printEmptyLine();
    }

    private static String framesInString(List<String> states) {
        return states.stream()
                .map(state -> StringUtils.center(state, FIXED_FRAME_SPACE))
                .collect(Collectors.joining(FRAME_SEPARATOR));
    }

    private static String scoresInString(List<ScoreDto> scores) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_SEPARATOR);
        stringBuilder.append(StringUtils.center(EMPTY_SCORE, FIXED_FRAME_SPACE));
        stringBuilder.append(FRAME_SEPARATOR);
        String scoresInString = scores.stream()
                .map(scoreDto -> scoreDto.isFullyCalculated() ? String.valueOf(scoreDto.currentScore()) : EMPTY_SCORE)
                .map(formattedScore -> StringUtils.center(formattedScore, FIXED_FRAME_SPACE))
                .collect(Collectors.joining(FRAME_SEPARATOR));
        stringBuilder.append(scoresInString);
        stringBuilder.append(FRAME_SEPARATOR);
        return stringBuilder.toString();
    }

    private static void printScoreBoardHeader(int totalNumberOfFrame) {
        System.out.print(playerNameHeaderAndFrameNumbersInString(totalNumberOfFrame));
        printEmptyLine();
    }

    private static String playerNameHeaderAndFrameNumbersInString(int totalNumberOfFrame) {
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
        return stringBuilder.toString();
    }

    private static String playerNameInString(String name) {
        return StringUtils.center(name, FIXED_FRAME_SPACE) + FRAME_SEPARATOR;
    }

}
