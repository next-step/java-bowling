package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrameFactory {
    private static final int MAX_SIZE = 10;

    public static List<Frame> of() {
        return Stream.generate(Frame::new)
                .limit(MAX_SIZE)
                .collect(Collectors.toList());
    }
}
