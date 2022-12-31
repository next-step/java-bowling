package bowling.domain.dto;

import bowling.domain.Frames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResultsDto {

    private final List<String> results;

    public FrameResultsDto(List<String> results) {
        this.results = makeResultsMaxSize(new ArrayList<>(results));
    }

    private List<String> makeResultsMaxSize(List<String> results) {
        int resultsSize = results.size();
        for (int i = 0; i < (Frames.MAX_FRAME_NUMBER - resultsSize); i++) {
            results.add("");
        }
        return results;
    }

    public List<String> results() {
        return Collections.unmodifiableList(results);
    }
}
