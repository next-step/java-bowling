package bowling.view;

import bowling.domain.FrameScoreResult;
import bowling.dto.*;
import bowling.util.StringUtils;

import java.util.List;

public class ResultView {

    public static final String FRAME_SEPARATOR = "|";

    private static final int FIXED_FRAME_SPACE = 6;

    private static final String HEADER_PLAYER_NAME_REPRESENTATION = "NAME";

    private static final String STRIKE_REPRESENTATION = "X";

    private static final String SPARE_REPRESENTATION = "/";

    private static final String GUTTER_REPRESENTATION = "-";

    private static final String EMPTY_PIN_COUNT_REPRESENTATION = "";

    public static void printScoreBoard(ScoreBoard scoreBoard) {
        printScoreBoardHeader(scoreBoard.totalNumberOfFrame());
        printScoreBoardBody(scoreBoard.playResult());
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private static void printScoreBoardBody(PlayResult playResult) {
        FramesResult framesResult = playResult.framesResult();
        String playerName = playerNameInString(playResult.playerName());
        String normalFramesInString = normalFramesInString(framesResult.normalFrameResults());
        String finalFrameInString = finalFrameInString(framesResult.finalFrameResult());

        System.out.print(playerName + normalFramesInString + finalFrameInString);
        printEmptyLine();
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
        return FRAME_SEPARATOR + StringUtils.center(name, FIXED_FRAME_SPACE);
    }

    private static String normalFramesInString(List<NormalFrameResult> normalFrameResults) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NormalFrameResult normalFrameResult : normalFrameResults) {
            stringBuilder.append(FRAME_SEPARATOR);
            stringBuilder.append(StringUtils.center(frameResultInString(normalFrameResult.frameResult()), FIXED_FRAME_SPACE));
        }
        return stringBuilder.toString();
    }

    private static String finalFrameInString(FinalFrameResult finalFrameResult) {
        List<FrameResult> frameResults = finalFrameResult.frameResults();
        String finalFrameResultInString = StringUtils.center(finalFrameResultInString(frameResults), FIXED_FRAME_SPACE);
        return FRAME_SEPARATOR + finalFrameResultInString + FRAME_SEPARATOR;
    }

    private static String finalFrameResultInString(List<FrameResult> frameResults) {
        if (frameResults.isEmpty()) {
            return EMPTY_PIN_COUNT_REPRESENTATION;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(frameResultInString(frameResults.get(0)));
        for (int i = 1; i < frameResults.size(); i++) {
            stringBuilder.append(FRAME_SEPARATOR);
            stringBuilder.append(frameResultInString(frameResults.get(i)));
        }
        return stringBuilder.toString();
    }

    private static String frameResultInString(FrameResult frameResult) {
        FrameScoreResult frameScoreResult = frameResult.frameScoreResult();
        List<Integer> pinCounts = frameResult.pinCounts();

        if (frameScoreResult == FrameScoreResult.STRIKE) {
            return strikeFrameResultInString();
        }

        if (frameScoreResult == FrameScoreResult.SPARE) {
            return spareFrameResultInString(pinCounts);
        }

        if (frameScoreResult == FrameScoreResult.MISS) {
            return missFrameResultInString(pinCounts);
        }

        if (frameScoreResult == FrameScoreResult.NONE) {
            return nonFrameResultInString(pinCounts);
        }

        return EMPTY_PIN_COUNT_REPRESENTATION;
    }

    private static String strikeFrameResultInString() {
        return STRIKE_REPRESENTATION;
    }

    private static String spareFrameResultInString(List<Integer> pinCounts) {
        Integer firstPinCount = pinCounts.get(0);
        return pinCountInString(firstPinCount) + FRAME_SEPARATOR + SPARE_REPRESENTATION;
    }

    private static String missFrameResultInString(List<Integer> pinCounts) {
        Integer firstPinCount = pinCounts.get(0);
        Integer secondPinCount = pinCounts.get(1);
        return pinCountInString(firstPinCount) + FRAME_SEPARATOR + pinCountInString(secondPinCount);
    }

    private static String nonFrameResultInString(List<Integer> pinCounts) {
        if (pinCounts.isEmpty()) {
            return EMPTY_PIN_COUNT_REPRESENTATION;
        }
        return pinCountInString(pinCounts.get(0));
    }

    private static String pinCountInString(Integer pinCount) {
        if (pinCount == 0) {
            return GUTTER_REPRESENTATION;
        }
        return pinCount.toString();
    }

}
