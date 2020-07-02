package bowling.domain.game;

import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private static final int MAX_FRAME = 10;

    private final Player player;
    private Frame currentFrame;
    private List<Frame> frames = new ArrayList<>();

    public Bowling(Player player) {
        this.player = player;
        this.currentFrame = NormalFrame.init();
    }

    public void bowl(int countOfPins) {
        if (!currentFrame.canBowling()) {
            this.frames.add(currentFrame);
        }

        this.currentFrame = this.currentFrame.next(countOfPins);
    }

    public boolean isEnd() {
        return frames.size() + 1 == MAX_FRAME && !this.currentFrame.canBowling();
    }

    @Override
    public String toString() {
        return "| " + player.toString() + " |" + getFrameString();
    }

    private String getFrameString() {
        StringBuilder frame = new StringBuilder();

        for (int i = 0; i < MAX_FRAME; i++) {
            frame.append(getFrameString(i)).append("|");
        }

        return frame.toString();
    }

    private String getFrameString(int index) {
        if (frames.size() > index) {
            return "  " + frames.get(index).toString() + "  ";
        }

        if (frames.size() == index) {
            return "  " + currentFrame.toString() + "  ";
        }

        return "      ";
    }

    public FrameNumber getFrameNumber() {
        if (isEnd()) {
            return FrameNumber.of(MAX_FRAME);
        }

        return FrameNumber.of(frames.size() + 1 + (currentFrame.canBowling() ? 0 : 1));
    }
}
