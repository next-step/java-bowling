package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {

    protected final int number;
    protected final List<Pin> pins;
    protected final List<String> results;

    protected Frame(int number) {
        this.number = number;
        this.pins = new ArrayList() {{
            add(Pin.from());
        }};
        this.results = new ArrayList<>();
    }

    public String hit(int count) {
        results.add(getLastPin().hit(count));
        return results.stream().collect(Collectors.joining("|"));
    }

    protected Pin getLastPin() {
        return pins.get(pins.size() - 1);
    }

    public int getNumber() {
        return number;
    }

    public boolean isFinish() {
        return getLastPin().isFinish();
    }

    public NormalFrame next() {
        return new NormalFrame(number + 1);
    }

    public boolean isLastFrame() {
        return false;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "number=" + number +
                ", pins=" + pins +
                ", results=" + results +
                '}';
    }
}