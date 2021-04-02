package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingFrameFactory {
    private static final int MAX_SIZE = 10;

    public static List<BowlingFrame> of() {
        return Stream.generate(BowlingFrame::new)
                .limit(MAX_SIZE)
                .collect(Collectors.toList());
    }
}
