package domain.base;

import domain.pin.Pin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseTest {
    protected List<Pin> getPins(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .mapToObj(number -> Pin.of(number))
                .collect(Collectors.toList());
    }

    protected List<Integer> getFrameNumbers(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .boxed()
                .collect(Collectors.toList());
    }
}
