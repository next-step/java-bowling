package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameResult {
    private static final int MAX_LENGTH = 3;

    private final List<Character> results;

    public FrameResult() {
        results = new ArrayList<>();
    }

    public void add(Character result) {
        if (results.size() >= MAX_LENGTH) {
            throw new IndexOutOfBoundsException("Size of Frame result must below 3.");
        }
        results.add(result);
    }
}
