package bowling.domain;

public class Player {

    private final String name;
    private final Frames frames;

    public Player(final String name) {

        this.name = name;
        this.frames = Frames.init();
    }

    public Frames getFrames() {

        return frames;
    }

    public boolean isGameEnd() {

        return frames.isLast();
    }

    public boolean canBowl() {

        return frames.lastFrame().canBowl();
    }

    public void bowl(final int number) {

        final Frame frame = frames.lastFrame();
        frame.bowl(number);

        if (!frame.isLastFrame() && frame.isFinished()) {
            frames.addFrame();
        }
    }

    public String getName() {

        return name;
    }
}
