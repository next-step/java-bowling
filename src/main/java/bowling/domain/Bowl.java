package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bowl {
    private final boolean isThrown;
    private final PinCount pinCount;

    public Bowl(boolean isThrown) {
        this(new PinCount(), isThrown);
    }

    public Bowl(int pinCount, boolean isThrown) {
        this(new PinCount(pinCount), isThrown);
    }

    public Bowl(PinCount pinCount, boolean isThrown) {
        this.pinCount = pinCount;
        this.isThrown = isThrown;
    }

    public boolean isStrike() {
        return pinCount.isStrike();
    }

    public int plusPinCountOf(Bowl firstBowl) {
        return pinCount.plus(firstBowl.pinCount);
    }

    public int plusPinCountOf(Bowl... bowls) {
        List<PinCount> pinCounts = Arrays.stream(bowls)
                .map(Bowl::knockedDownPinCount)
                .collect(Collectors.toList());

        return pinCount.plus(pinCounts);
    }

    public PinCount knockedDownPinCount() {
        return pinCount;
    }

    public boolean isThrown() {
        return isThrown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowl bowl = (Bowl) o;
        return Objects.equals(pinCount, bowl.pinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}
