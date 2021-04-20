package bowling.domain.state;

public interface FrameState {

    FrameState bowl(int pin);

    String printResult();

    default String isGutter(int pin) {
        if (pin == 0) {
            return "-";
        }
        return String.valueOf(pin);
    }
}
