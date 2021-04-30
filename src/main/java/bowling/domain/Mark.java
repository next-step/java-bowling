package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Mark {
    private static final int FIRST_PIN_COUNT_INDEX = 0;
    private static final int SECOND_PIN_COUNT_INDEX = 1;
    private static final int THIRD_PIN_COUNT_INDEX = 2;

    private final Frame frame;
    private List<String> symbols;

    public Mark(Frame frame) {
        this.frame = frame;
        this.symbols = new ArrayList<>();
    }

    public List<String> symbols() {
        List<PinCount> pinCounts = frame.pinCounts().pinCounts();

        if (pinCounts.size() == 1) {
            symbols.add(firstSymbol(pinCounts));
        }

        if (pinCounts.size() == 2) {
            symbols.addAll(Arrays.asList(firstSymbol(pinCounts), secondSymbol(pinCounts)));
        }

        if (pinCounts.size() == 3) {
            symbols.addAll(Arrays.asList(firstSymbol(pinCounts), secondSymbol(pinCounts), thirdSymbol(pinCounts)));
        }

        return symbols;
    }

    private String firstSymbol(List<PinCount> pinCounts) {
        return Symbol.ofFirst(pinCounts.get(FIRST_PIN_COUNT_INDEX));
    }

    private String secondSymbol(List<PinCount> pinCounts) {
        return Symbol.ofSecond(pinCounts.get(FIRST_PIN_COUNT_INDEX), pinCounts.get(SECOND_PIN_COUNT_INDEX));
    }

    private String thirdSymbol(List<PinCount> pinCounts) {
        return Symbol.ofThird(pinCounts.get(FIRST_PIN_COUNT_INDEX), pinCounts.get(SECOND_PIN_COUNT_INDEX), pinCounts.get(THIRD_PIN_COUNT_INDEX));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark that = (Mark) o;
        return Objects.equals(frame, that.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame);
    }
}
