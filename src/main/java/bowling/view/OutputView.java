package bowling.view;

import bowling.domain.state.FrameBowlState;
import bowling.domain.state.FrameBowlStates;
import bowling.domain.state.ScoreType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final int FRAME_COUNT = 10;

    private static final Map<ScoreType, String> displayMap = new HashMap<>();

    static {
        displayMap.put(ScoreType.STRIKE, "X");
        displayMap.put(ScoreType.SPARE, "/");
        displayMap.put(ScoreType.GUTTER, "-");
    }

    public void printResult(String name, List<FrameBowlStates> frameResults) {
        printFramesRounds();
        printFramesResult(name, frameResults);
    }

    private void printFramesRounds() {
        StringBuilder framesBuilder = new StringBuilder();
        framesBuilder.append("| NAME |");

        for (int i = 0; i < FRAME_COUNT; i++) {
            framesBuilder.append(String.format("  %02d  |", i + 1));
        }
        framesBuilder.append(System.lineSeparator());
        System.out.println(framesBuilder.toString());
    }

    private void printFramesResult(String name, List<FrameBowlStates> frameResults) {
        StringBuilder downPinBuilder = new StringBuilder();
        downPinBuilder.append(String.format("|  %s |", name));

        for (FrameBowlStates frameBowlStates : frameResults) {
            String downPinDisplay = createDisplay(frameBowlStates.getFrameBowlStates());
            downPinBuilder.append(String.format("  %-4s|", downPinDisplay));
        }

        System.out.println(downPinBuilder.toString());
    }


    private String createDisplay(List<FrameBowlState> bowls) {
        if (bowls.size() == 0) {
            return "";
        }

        return bowls.stream()
            .map(frameBowlState -> displayMap.getOrDefault(frameBowlState.getScoreType(),
                String.valueOf(frameBowlState.getDownPin())))
            .collect(Collectors.joining("|"));
    }
}
