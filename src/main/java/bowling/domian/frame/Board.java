package bowling.domian.frame;

import java.util.List;

public class Board {

    private Board() {
    }

    public static Board init() {
        return new Board();
    }

    public void addResult(FrameResult frameResult, int frameNumber) {
    }

    public List<String> getBowlResults() {
        return null;
    }

    public List<Integer> getScoreResults() {
        return null;
    }
}
