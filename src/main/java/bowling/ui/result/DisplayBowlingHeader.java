package bowling.ui.result;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public final class DisplayBowlingHeader implements Display {

    @Override
    public String toString() {
        return toDisplay();
    }

    @Override
    public String getName() {
        return "NAME";
    }

    private String leftPaddingZero(String n){
        if (1 == n.length()) {
            return '0' + n;
        }
        return n;
    }

    @Override
    public String toResults() {
        return IntStream.rangeClosed(1,10)
                        .mapToObj(String::valueOf)
                        .map(this::leftPaddingZero)
                        .map(this::toResult)
                        .collect(joining("|"));
    }
}
