package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames(new NormalFrame());
    }

    public void play(Pin pin) {
        Frame frame = this.frames.getCurrentFrame();
        frame.bowl(pin);

        if (frame.isEndFrame()) {
            this.frames.add(frame.getNextFrame(getCurrentFrameNumber()));
        }
    }

    public boolean isEndGame() {
        return this.frames.isEndGame();
    }

    public int getCurrentFrameNumber() {
        return this.frames.getCurrentFrameNumber();
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }
}

