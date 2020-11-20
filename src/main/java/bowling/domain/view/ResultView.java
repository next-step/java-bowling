package bowling.domain.view;

import bowling.domain.frame.FrameResult;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String BLANK = "";
    private static final String BLOCK = "|";
    private static final String MARGIN_BLOCK = "|      |";
    private static final int FRAME_COUNT = 10;
    private static final int STRIKE_PIN = 10;
    private static final int GUTTER_PIN = 0;
    public static final String MARK_SPARE = "/";
    public static final String MARK_STRIKE = "X";
    public static final String MARK_GUTTER = "-";
    public static final String BOARD_NAME = "| NAME |";

    public void printResult(Player player, List<FrameResult> frameResults) {
        printBoardTitle();
        printBoard(player.toString(), frameResults);
        printScores(frameResults.stream().map(FrameResult::getScore).collect(Collectors.toList()));
        System.out.print(System.lineSeparator());
    }

    private void printBoardTitle() {
        StringBuilder framesBuilder = new StringBuilder();
        framesBuilder.append(BOARD_NAME);
        for (int i = 0; i < FRAME_COUNT; i++) {
            framesBuilder.append(String.format("  %02d  |", i + 1));
        }
        System.out.println(framesBuilder.toString());
    }

    private void printBoard(String name, List<FrameResult> frameResults) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|  %s |", name));

        for (FrameResult frameResult : frameResults) {
            String downPinDisplay = getScoreSymbol(frameResult);
            sb.append(String.format("  %-4s|", downPinDisplay));
        }
        System.out.println(sb.toString());
    }

    private String getScoreSymbol(FrameResult frameResult) {
        List<Integer> downPins = frameResult.getDownPins();
        if (downPins.isEmpty()) {
            return BLANK;
        }
        List<String> components = new ArrayList<>();

        int downPinIndex = 0;
        components.add(convertMarks(downPins.get(downPinIndex++)));

        ScoreType scoreType = frameResult.getScoreType();
        if (scoreType == ScoreType.SPARE) {
            components.add(MARK_SPARE);
            downPinIndex++;
        }
        for (; downPinIndex < downPins.size(); downPinIndex++) {
            components.add(convertMarks(downPins.get(downPinIndex)));
        }
        return components.stream().collect(Collectors.joining(BLOCK));
    }

    private static void printScores(List<Score> scores) {
        StringBuilder scoreDisplays = new StringBuilder();
        scoreDisplays.append(MARGIN_BLOCK);
        int sum = 0;
        for (Score score : scores) {
            sum += score.getValue();
            scoreDisplays.append(String.format("  %-4s|", score.getScoreType() != ScoreType.READY ? sum : BLANK));
        }
        System.out.println(scoreDisplays.toString());
    }

    private String convertMarks(int downPin) {
        if (downPin == STRIKE_PIN) {
            return MARK_STRIKE;
        }
        if (downPin == GUTTER_PIN) {
            return MARK_GUTTER;
        }
        return String.valueOf(downPin);
    }


}
