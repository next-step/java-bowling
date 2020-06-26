package bowling.view;

import bowling.domain.frame.FrameResult;
import bowling.domain.ScoreType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FRAME_COUNT = 10;

    private static final Map<ScoreType, String> displayMap = new HashMap<>();

    static {
        displayMap.put(ScoreType.STRIKE, "X");
        displayMap.put(ScoreType.SPARE, "/");
    }

    public void printResult(String name, List<FrameResult> frameResults) {
        printFramesRounds();
        printBowlsResult(name, frameResults);
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
        List<String> components = new ArrayList<>();

        int downPinIndex = 0;

        ScoreType scoreType = frameResult.getScoreType().orElse(ScoreType.MISS);
        if (scoreType == ScoreType.STRIKE) {
            downPinIndex++;
            components.add("X");
        }else if(scoreType == ScoreType.SPARE) {
            components.add(createPin(downPins.get(downPinIndex++)));
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
        for(Optional<Integer> score : scores){
            sum+= score.orElse(0);
            scoreDisplays.append(String.format("  %-4s|", score.isPresent()? sum : "") );
        }
        System.out.println(scoreDisplays.toString());
    }

    private String createPin(int downPin) {
        if(downPin == 0){
            return "-";
        }

        if(downPin == 10){
            return "X";
        }

        return String.valueOf(downPin);
    }

}
