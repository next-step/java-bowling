package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinGenerator {
    private static final int RANGE_BEGIN_PIN_NUMBER = 1;
    private static final int RANGE_END_PIN_NUMBER = 10;

    public static List<Pin> generate() {
        return IntStream.rangeClosed(RANGE_BEGIN_PIN_NUMBER, RANGE_END_PIN_NUMBER)
                .boxed()
                .map(Pin::new)
                .collect(Collectors.toList());
    }
}
