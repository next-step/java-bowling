package bowling.domain;

public class BowlingGame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames(new NormalFrame());
    }

    public void play(Pin pin) {
        Frame frame = this.frames.getCurrentFrame();
        int previousFallenPin = frame.getStates().getLastPin().getFallenPin();
        int currentFallenPin = pin.getFallenPin();

        frame.bowl(previousFallenPin, currentFallenPin);

        if (frame.isEndFrame()) {
            this.frames.add(frame.getNextFrame(getCurrentFrameNumber()));
        }
    }

    public boolean isEndFrame(int frameNumber) {
        if (frameNumber == FINAL_FRAME_NUMBER) {
            return frames.isEndGame();
        }
        return frames.getFrame(frameNumber).isEndFrame();
    }

    public boolean isEndGame() {
        return this.frames.isEndGame();
    }

    public int getCurrentFrameNumber() {
        return this.frames.getCurrentFrameNumber();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }
}

