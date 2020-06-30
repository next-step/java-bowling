package bowling.ui;

import bowling.domain.player.Player;
import bowling.domain.score.ScoreType;
import bowling.domain.frame.FrameResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultView {

    private static final int FRAME_COUNT = 10;
    private static final int STRIKE_PIN = 10;
    private static final int GUTTER_PIN = 0;

    private static final Map<ScoreType, String> displayMap = new HashMap<>();

    static {
        displayMap.put(ScoreType.STRIKE, "X");
        displayMap.put(ScoreType.SPARE, "/");
    }

    public void printResult(Player player, List<FrameResult> frameResults) {
        printFramesRounds();
        printBowlsResult(player.toString(), frameResults);
        printScores(frameResults.stream().map(FrameResult::getScore).collect(Collectors.toList()));
        System.out.print(System.lineSeparator());
    }

    private void printFramesRounds() {
        StringBuilder framesBuilder = new StringBuilder();
        framesBuilder.append("| NAME |");

        for (int i = 0; i < FRAME_COUNT; i++) {
            framesBuilder.append(String.format("  %02d  |", i + 1));
        }
        System.out.println(framesBuilder.toString());
    }

    private void printBowlsResult(String name, List<FrameResult> frameResults) {
        StringBuilder downPinBuilder = new StringBuilder();
        downPinBuilder.append(String.format("|  %s |", name));

        for (FrameResult frameResult : frameResults) {
            String downPinDisplay = createBowlDisplay(frameResult);
            downPinBuilder.append(String.format("  %-4s|", downPinDisplay));
        }

        System.out.println(downPinBuilder.toString());
    }

    private String createBowlDisplay(FrameResult frameResult) {
        List<Integer> downPins = frameResult.getDownPins();
        if(downPins.isEmpty()){
            return "";
        }

        List<String> components = new ArrayList<>();

        int downPinIndex = 0;
        components.add(createPin(downPins.get(downPinIndex++)));

        ScoreType scoreType = frameResult.getScoreType().orElse(ScoreType.MISS);
        if (scoreType == ScoreType.SPARE) {
            components.add("/");
            downPinIndex++;
        }

        for (; downPinIndex < downPins.size(); downPinIndex++) {
            components.add(createPin(downPins.get(downPinIndex)));
        }
        return components.stream().collect(Collectors.joining("|"));
    }

    private void printScores(List<Optional<Integer>> scores) {
        StringBuilder scoreDisplays = new StringBuilder();
        scoreDisplays.append("|      |");
        int sum = 0;
        for (Optional<Integer> score : scores) {
            sum += score.orElse(0);
            scoreDisplays.append(String.format("  %-4s|", score.isPresent() ? sum : ""));
        }
        System.out.println(scoreDisplays.toString());
    }

    private String createPin(int downPin) {
        if (downPin == GUTTER_PIN) {
            return "-";
        }

        if (downPin == STRIKE_PIN) {
            return "X";
        }

        return String.valueOf(downPin);
    }

}
