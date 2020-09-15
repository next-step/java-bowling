package bowling.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();
    private Player player;

    public Frames(Player player) {
        this.player = player;
        this.frames.add(new NormalFrame(Frame.MIN_FRAME_INDEX));
    }

    public void play(int countOfPins) {
        Frame currentFrame = getLastFrame();
        currentFrame.bowl(countOfPins);

        if (currentFrame.rollingEnd() && !currentFrame.allFrameEnd()) {
            frames.add(currentFrame.next());
        }
    }

    public String currentFrame() {
        return String.valueOf(frames.size());
    }

    public boolean allFrameEnd() {
        return getLastFrame().allFrameEnd();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public String getPlayerName() {
        return player.name();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int size() {
        return frames.size();
    }
}
