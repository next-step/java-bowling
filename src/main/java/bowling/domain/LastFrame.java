package bowling.domain;

import java.util.stream.Collectors;

public class LastFrame extends Frame {

    protected LastFrame(int number) {
        super(number);
    }

    public static Frame from() {
        return new LastFrame(10);
    }

    @Override
    public String hit(int count) {
        String result = getLastPin().hit(count);
        results.add(result);

        int pinsSize = pins.size();

        if (pinsSize == 1 && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pinsSize == 2 && getPrevResult().equals("X") && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pinsSize == 1 && result.equals("/")) {
            pins.add(Pin.of(1, 0));
        }

        return results.stream().collect(Collectors.joining("|"));
    }

    private String getPrevResult() {
        return results.get(results.size() - 2);
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
