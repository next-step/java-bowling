package bowling.view;

import bowling.domain.state.PinsState;
import bowling.domain.state.ScoreType;
import java.util.ArrayList;
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
    }

    public void printResult(String name, List<PinsState> pinsStates) {
        printFramesRounds();
        printFramesResult(name, pinsStates);
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

    private void printFramesResult(String name, List<PinsState> pinsStates) {
        StringBuilder downPinBuilder = new StringBuilder();
        downPinBuilder.append(String.format("|  %s |", name));

        for (PinsState pinsState : pinsStates) {
            String downPinDisplay = createDisplay(pinsState);
            downPinBuilder.append(String.format("  %-4s|", downPinDisplay));
        }

        System.out.println(downPinBuilder.toString());
    }

    private String createDisplay(PinsState pinsState) {

        List<Integer> downPins = pinsState.getDownPins();

        List<String> components = new ArrayList<>();

        int downPinIndex = 0;
        for (ScoreType scoreType : pinsState.getScoreTypes()) {
            if (scoreType == ScoreType.STRIKE) {
                downPinIndex++;
                components.add("X");
                continue;
            }

            if (scoreType == ScoreType.SPARE) {
                int downPin = pinsState.getDownPins().get(downPinIndex++);
                components.add(createPin(downPin));

                downPinIndex++;
                components.add("/");
            }
        }

        for (; downPinIndex < downPins.size(); downPinIndex++) {
            components.add(createPin(pinsState.getDownPins().get(downPinIndex)));
        }

        return components.stream().collect(Collectors.joining("|"));
    }

    private String createPin(int downPin) {
        return downPin == 0 ? "-" : String.valueOf(downPin);
    }

}
