package bowling;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Frame> frames;

    public ScoreBoard() {
        this(new ArrayList<>());
    }

    public ScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }
}
