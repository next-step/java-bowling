package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private Map<Frame, Integer> scoreMap;

    public Score() {
        scoreMap = new HashMap<>();
        initMap();
    }

    private void initMap() {
        for (Frame value : Frame.values()) {
            scoreMap.put(value, 0);
        }
    }

    public void recordScore(Frame frame, int score) {
        scoreMap.put(frame, score);
    }

    public int frameScore(Frame frame) {
        return scoreMap.get(frame);
    }
}
