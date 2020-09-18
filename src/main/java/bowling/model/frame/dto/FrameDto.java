package bowling.model.frame.dto;

import bowling.model.Result;

import java.util.Collections;
import java.util.List;

public class FrameDto {
    private final int index;
    private final List<Result> results;

    public FrameDto(int index, List<Result> results) {
        this.index = index;
        this.results = Collections.unmodifiableList(results);
    }

    public int getIndex() {
        return index;
    }

    public List<Result> getResults() {
        return results;
    }
}
