package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame extends Frame {

    public static Frame from() {
        return new LastFrame();
    }

    public String hit(int count) {
        String result = getLastPin().hit(count);

        if (pins.size() == 2 && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pins.size() == 1 && result.equals("X")) {
            pins.add(Pin.from());
        }

        if (pins.size() == 1 && result.equals("/")) {
            pins.add(Pin.of(1,0));
        }

        results.add(result);

        return results.stream().collect(Collectors.joining("|"));
    }
}
