package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames;

    private Game() {
        this.frames = new ArrayList<Frame>() {{
            add(NormalFrame.from());
        }};
    }

    public static Game start() {
        return new Game();
    }

    public boolean isFinish() {
        return getLast().isFinish();
    }

    public String hit(int count) {
        Frame last = getLast();
        String result = last.hit(count);

        if (last.getNumber() == 9 && last.isFinish()) {
            this.frames.add(LastFrame.from());
            return result;
        }

        if (!last.isLastFrame() && last.isFinish()) {
            this.frames.add(last.next());
        }

        return result;
    }

    public int getPlayNumber() {
        return getLast().getNumber();
    }

    private Frame getLast() {
        return this.frames.get(this.frames.size() - 1);
    }
}
