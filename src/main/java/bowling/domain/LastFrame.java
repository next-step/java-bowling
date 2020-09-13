package bowling.domain;

import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame(int number) {
        super(number);
    }

    public static Frame from() {
        return new LastFrame(10);
    }

    @Override
    public List<String> hit(int count) {
        String result = getLastPin().hit(count);
        List<String> status = state.store(result);

        int pinsSize = pins.size();

        if (pinsSize == 1 && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pinsSize == 2 && status.get(status.size() - 2).equals("X") && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pinsSize == 1 && result.equals("/")) {
            pins.add(Pin.of(1, 0));
        }

        return status;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
