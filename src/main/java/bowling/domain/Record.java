package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Record {
    private final List<Integer> records = new ArrayList<>();

    void record(final int numberOfFallenPins) {
        records.add(numberOfFallenPins);
    }

    public List<Integer> getRecords() {
        return Collections.unmodifiableList(records);
    }
}
