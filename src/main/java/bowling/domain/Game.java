package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames;

    private Game() {
        frames = new ArrayList<Frame>() {{
            add(NormalFrame.from());
        }};
    }

    public static Game start() {
        return new Game();
    }

    public boolean isFinish() {
        return getLastFrame().isFinish();
    }

    public int getPlayNumber() {
        return getLastFrame().getNumber();
    }

    public String hit(int count) {
        Frame last = getLastFrame();
        String result = last.hit(count);

        if (last.canGoNextFrame()) {
            addNextFrame();
        }

        return result;
    }

    private void addNextFrame() {
        frames.add(getLastFrame().next());
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
