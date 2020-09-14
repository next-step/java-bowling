package bowling.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private List<Frame> frames;
    private Map<Integer, List<String>> results;

    private Game() {
        frames = new ArrayList<Frame>() {{
            add(NormalFrame.from());
        }};
        results = new HashMap<>();
    }

    public static Game start() {
        return new Game();
    }

    public boolean isFinish() {
        Frame lastFrame = getLastFrame();
        return !lastFrame.hasNextFrame() && lastFrame.isFinish();
    }

    public int getPlayNumber() {
        return getLastFrame().getNumber();
    }

    public List<List<String>> hit(int count) {
        Frame lastFrame = getLastFrame();
        Frame nextFrame = lastFrame.hit(count);

        results.put(nextFrame.getNumber(), nextFrame.value());

        addNextFrame(nextFrame);

        return toHitResult();
    }

    private void addNextFrame(Frame nextFrame) {
        Frame lastFrame = getLastFrame();

        if (lastFrame.getNumber() != nextFrame.getNumber()) {
            frames.add(nextFrame);
        }
    }

    private List<List<String>> toHitResult() {
        return results.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
