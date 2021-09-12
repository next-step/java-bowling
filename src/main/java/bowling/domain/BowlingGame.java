package bowling.domain;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
    public static final int MAX_FRAME_SIZE = 10;
    public static final int LAST_FRAME_INDEX = 9;
    public static final int FIRST_FRAME_INDEX = 1;

    private final Name playerName;
    private List<Frame> frames = new LinkedList<>();

    public BowlingGame(final String playerName) {
        this.playerName = new Name(playerName);
        frames.add(new NormalFrame(FIRST_FRAME_INDEX));
    }

    public String player() {
        return playerName.value();
    }

    public List<Frame> frames() {
        return frames;
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_SIZE
                && frames.get(LAST_FRAME_INDEX).isEnd();
    }

    public void pitch(final int countOfPins) {
        Frame frame = currentFrame().pitch(countOfPins);

        if (currentFrame().isEnd()) {
            frames.add(frame);
        }
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }
}
