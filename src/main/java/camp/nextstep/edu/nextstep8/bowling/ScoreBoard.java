package camp.nextstep.edu.nextstep8.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreBoard {
    private final static int DEFAULT_FRAME_SIZE = 11;

    private final List<Frame> frames;

    public ScoreBoard() {
        frames = generateFrame();
    }

    public void markScore(int frameNumber, int score) {
        frames.add(new Frame(frameNumber, score));
    }

    public Optional<Frame> get(int frameNumber) {
        return frames.stream()
                .filter(f -> f.match(frameNumber) == true)
                .findFirst();
    }

    public int size() {
        return frames.size();
    }

    private List<Frame> generateFrame() {
        List<Frame> frames = new ArrayList<>();
        for(int i = 0; i < DEFAULT_FRAME_SIZE; i++){
            frames.add(new Frame(i, 0));
        }
        return frames;
    }
}
