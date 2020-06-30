package bowling.view;

import bowling.domain.Player;
import bowling.domain.ScoreType;
import bowling.domain.frame.FrameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameBowlView {

    private static final int STRIKE_PIN = 10;
    private static final int GUTTER_PIN = 0;

    void printBowlsResult(Player player, List<FrameResult> frameResults) {
        StringBuilder downPinBuilder = new StringBuilder();
        downPinBuilder.append(String.format("|  %s |", player.getName()));

        for (FrameResult frameResult : frameResults) {
            String downPinDisplay = createBowlDisplay(frameResult);
            downPinBuilder.append(String.format("  %-4s|", downPinDisplay));
        }

        System.out.println(downPinBuilder.toString());
    }

    private String createBowlDisplay(FrameResult frameResult) {
        List<Integer> downPins = frameResult.getDownPins();
        if (downPins.isEmpty()) {
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
