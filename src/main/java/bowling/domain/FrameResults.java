package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameResults {

    private List<FrameResult> frameResults;

    private FrameResults(List<FrameResult> frameResults) {
        this.frameResults = new ArrayList<>(frameResults);
    }


}
