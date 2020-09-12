package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    protected final List<Pin> pins;
    protected final List<String> results;

    public Frame() {
        this.pins = new ArrayList() {{
            add(Pin.from());
        }};
        this.results = new ArrayList<>();
    }

    public static Frame from() {
        return new Frame();
    }

    public String hit(int count) {
        results.add(getLastPin().hit(count));
        return results.stream().collect(Collectors.joining("|"));
    }

    protected Pin getLastPin() {
        return pins.get(pins.size() - 1);
    }
}
