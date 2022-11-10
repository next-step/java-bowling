package bowling;

import java.util.HashMap;
import java.util.Map;

import bowling.domain.Frame;

public class ResultScore {
    private Map<Frame, Integer> score;

    public ResultScore(Map<Frame, Integer> score) {
        this.score = score;
    }
}
