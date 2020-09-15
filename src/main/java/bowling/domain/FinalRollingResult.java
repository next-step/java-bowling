package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.common.BowlingConstant.*;

public class FinalRollingResult {

    private final List<Pin> rollingPin;
    private boolean extraFrame = false;

    private List<String> desc = new ArrayList<>();

    public FinalRollingResult() {
        this.rollingPin = new ArrayList<>();
    }

    public void bowl(int countOfPins) {
        rollingPin.add(new Pin(countOfPins));

        lastDesc(countOfPins);
        isCanExtraFrame(countOfPins);
    }

    private void lastDesc(int countOfPins) {
        if (countOfPins == 10) {
            desc.add(BOWLING_STATUS_STRIKE);
            return;
        }

        if (rollingPin.size() == 2 && countOfIndexPins(0, 1) == PIN_COUNT_TOTAL) {
            desc.add(BOWLING_STATUS_SPARE);
            return;
        }

        if (rollingPin.size() == 3 && countOfIndexPins(1, 2) == PIN_COUNT_TOTAL) {
            desc.add(BOWLING_STATUS_SPARE);
            return;
        }

        desc.add(transGutter(countOfPins));
    }

    private void isCanExtraFrame(int countOfPins) {
        if (countOfPins == 10 || countOfFirstAndSecondPins() == PIN_COUNT_TOTAL) {
            extraFrame = true;
        }
    }

    public boolean isFinish() {
        return rollingPin.size() > 1 && !extraFrame || rollingPin.size() > 2;
    }

    public String desc() {
        if (rollingPin.isEmpty()) {
            return "";
        }

        return this.desc.stream().collect(Collectors.joining(BOWLING_SCORE_SEPERATOR));
    }

    private int countOfIndexPins(int index1, int index2) {
        return rollingPin.get(index1).count() + rollingPin.get(index2).count();
    }

    private int countOfFirstAndSecondPins() {

        return rollingPin.stream().limit(2).map(pin -> pin.count()).reduce((a, b) -> a + b).orElse(-1);
    }

    private String transGutter(int countOfPins) {
        String pinCount = String.valueOf(countOfPins);
        if (pinCount.equals(BOWLING_FALLEN_PIN_ZERO)) {
            return BOWLING_STATUS_GUTTER;
        }
        return pinCount;
    }
}
