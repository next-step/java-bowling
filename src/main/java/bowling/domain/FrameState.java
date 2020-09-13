package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameState {

    private final int number;
    private final List<String> results;

    private FrameState(int number) {
        this.number = number;
        this.results = new ArrayList<>();
    }

    public static FrameState from(int number) {
        return new FrameState(number);
    }

    public int getNumber() {
        return number;
    }

    public List<String> store(String result) {
        results.add(result);
        return Collections.unmodifiableList(results);
    }

    public List<String> getResult() {
        return Collections.unmodifiableList(results);
    }

    @Override
    public String toString() {
        return "FrameState{" +
                "number=" + number +
                ", results=" + results +
                '}';
    }
}
