package bowling.domain;

public class BowlingGame {
    public static final int MAX_FRAME_SIZE = 10;

    private final Name playerName;
    private final Frames frames;

    public BowlingGame(final String playerName) {
        this.playerName = new Name(playerName);
        frames = new Frames();
    }

    public String player() {
        return playerName.value();
    }

    public Frames frames() {
        return frames;
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_SIZE
                && frames.getLast().isEnd();
    }

    public boolean pitch(final int countOfPins) {
        Frame frame = currentFrame().pitch(countOfPins);

        if (currentFrame().isEnd() && currentFrame().isNormal()) {
            frames.add(frame.next());
            return true;
        }
        return false;
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public int currentFrameNumber() {
        return frames.size();
    }

}
