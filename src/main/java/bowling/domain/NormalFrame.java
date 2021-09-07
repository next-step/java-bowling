package bowling.domain;

import bowling.exception.InputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MAX_ORDER = 2;
    private static final String EXCESS_MAX_ORDER_ERROR = "투구는 " + MAX_ORDER + "번만 가능합니다.";
    private static final String EXCESS_MAX_PIN_COUNT_ERROR = "쓰러트릴 수 있는 핀의 갯수는 최대 " + MAX_PIN_COUNT + "개 입니다.";

    private List<Integer> pins = new ArrayList<>();
    private FrameNumber frameNumber;

    public NormalFrame(final int countOfPin, final int frameNumber) {
        if (countOfPin > MAX_PIN_COUNT) {
            throw new InputException(EXCESS_MAX_PIN_COUNT_ERROR);
        }
        pins.add(countOfPin);
        this.frameNumber = new FrameNumber(frameNumber);
    }

    @Override
    public Frame next(final int countOfPin) {
        if (!isFinishFrame()) {
            pins.add(countOfPin);
            return this;
        }
        if (frameNumber.isLastNumber()) {
            return new FinalFrame(countOfPin);
        }
        return new NormalFrame(countOfPin, frameNumber.valueOfNextNumber());
    }

    private boolean isFinishFrame() {
        int countOfScores = pins.size();
        int sum = pins.stream().reduce(0, Integer::sum);
        if (countOfScores == 1 && sum == MAX_PIN_COUNT) {
            return true;
        }
        return countOfScores == MAX_ORDER;
    }

    @Override
    public String resultOfFrame() {
        if (!isFinishFrame()) {
            throw new IllegalArgumentException("");
        }
        ScoreType scoreType = ScoreType.of(
                pins.size(),
                pins.stream().reduce(0, Integer::sum)
        );

        return pins.stream()
                .filter(pin -> pin != 10)
                .map(pin -> String.valueOf(pin) + scoreType.getSymbol())
                .findFirst()
                .orElse(scoreType.getSymbol());
    }

}
