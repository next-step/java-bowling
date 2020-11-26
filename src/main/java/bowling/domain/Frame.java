package bowling.domain;

public class Frame {
    private final CountOfPins first;
    private final CountOfPins second;

    Frame(CountOfPins first, CountOfPins second) {
        validate(first, second);
        this.first = first;
        this.second = second;
    }

    private void validate(CountOfPins first, CountOfPins second) {
        first.sum(second);
    }

    public FrameEnum getEnum() {
        return FrameEnum.of(first, second);
    }
}
