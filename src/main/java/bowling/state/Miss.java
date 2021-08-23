package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Miss implements State {
    private final List<Pin> downedPins;

    private Miss(final Pin... downedPins) {
        this.downedPins = Arrays.asList(downedPins);
    }

    public static Miss from(final Pin... downedPins) {
        return new Miss(downedPins);
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        throw new EndStateException("더 이상 진행할 수 없습니다.");
    }

    @Override
    public List<Integer> getScore() {
        return downedPins.stream()
                .map(Pin::parseInt)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isClean() {
        return false;
    }
}
