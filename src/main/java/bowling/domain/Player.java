package bowling.domain;

public class Player {
    private final String name;
    private final Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = Frames.init();
    }

    public void bowl(int value) {
        frames.bowl(value);
    }

    public String getName() {
        return name;
    }

    public boolean isNthFrameOver(int index) {
        return nthFrame(index).isOver();
    }

    public Frame nthFrame(int n) {
        return frames.get(n - 1);
    }
}
