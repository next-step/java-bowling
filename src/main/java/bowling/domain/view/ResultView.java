package bowling.domain.view;

import bowling.domain.frame.FrameResult;
import bowling.domain.player.Player;
import bowling.domain.score.Score3;
import bowling.domain.score.ScoreType2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    private static final String BLANK = "";
    private static final String BLOCK = "|";
    private static final String MARGIN_BLOCK = "    ";
    private static final int FRAME_COUNT = 10;
    private static final int STRIKE_PIN = 10;
    private static final int GUTTER_PIN = 0;
    public void printResult(Player player, List<FrameResult> frameResults) {
        printFrames();
        printBoard(player.toString(), frameResults);
        printScores(frameResults.stream().map(FrameResult::getScore).collect(Collectors.toList()));
        System.out.print(System.lineSeparator());
    }

    private void printFrames() {
        StringBuilder framesBuilder = new StringBuilder();
        framesBuilder.append("| NAME |");
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
        if(downPins.isEmpty()){
            return "";
        }
        List<String> components = new ArrayList<>();

        int downPinIndex = 0;
        components.add(createPin(downPins.get(downPinIndex++)));

        ScoreType2 scoreType = frameResult.getScoreType();
        if (scoreType == ScoreType2.SPARE) {
            components.add("/");
            downPinIndex++;
        }
        for (; downPinIndex < downPins.size(); downPinIndex++) {
            components.add(createPin(downPins.get(downPinIndex)));
        }
        return components.stream().collect(Collectors.joining("|"));
    }


    private static void printScores(List<Score3> scores) {
        StringBuilder scoreDisplays = new StringBuilder();
        scoreDisplays.append("|      |");
        int sum = 0;
        for (Score3 score :scores) {
            sum += score.getValue();
            scoreDisplays.append(String.format("  %-4s|", score.getScoreType() != ScoreType2.READY ? sum : ""));
        }
        System.out.println(scoreDisplays.toString());
    }

    private String createPin(int downPin) {
        if (downPin == STRIKE_PIN) {
            return "X";
        }
        if (downPin == GUTTER_PIN) {
            return "-";
        }
        return String.valueOf(downPin);
    }

    private static String margin(String input) {
        return String.format("  %-4s|", input);
    }

}
