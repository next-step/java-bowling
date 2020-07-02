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
        currentFrame = currentFrame.next(countOfPins);

        if (!currentFrame.canBowling()) {
            this.frames.add(currentFrame);
        }
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME;
    }

    @Override
    public String toString() {
        return " | " + player.toString() + " | " + getFrameString();
    }

    private String getFrameString() {
        String frame = "";
        for (int i = 0; i < MAX_FRAME; i++) {
            frame += getFrameString(frame, i) + "|";
        }
        return frame;
    }

    private String getFrameString(String frame, int index) {
        if (frames.size() > index) {
            return frames.get(index).toString();
        }

        return "    ";
    }
}
