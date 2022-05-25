package bowling.domain.bowl;

public class Bowl {
    private final PinCount pinCount;

    public Bowl(PinCount pinCount) {
        validate(pinCount);
        this.pinCount = pinCount;
    }

    private void validate(PinCount pinCount) {
        if (pinCount == null) {
            throw new IllegalArgumentException("pinCount는 null 일 수 없습니다.");
        }
    }
}
