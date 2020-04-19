package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.point.Point;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int LAST_FRAME_INDEX = 9;
    private static final int TOTAL_NORMAL_FRAME = 9;

    private final Player player;
    private final List<Frame> frames;

    public Frames(Player player) {
        this.player = player;
        this.frames = initFrames();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame getFrame(int index) {
        return this.frames.get(index);
    }

    public Frame getNextFrame() {
        return this.frames.stream()
                .filter(frame -> frame.isThrowable())
                .findFirst()
                .orElseGet(() -> frames.get(LAST_FRAME_INDEX));
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getFrameNo(Frame frame) {
        int frameNo = 0;
        Frame targetFrame = frames.get(frameNo);
        while (!frame.equals(targetFrame)) {
            frameNo++;
            targetFrame = frames.get(frameNo);
        }
        return frameNo + 1;
    }

    public void calculateScores(Point point) {
        this.frames.stream()
                .filter(frame -> frame.hasScore())
                .forEach(frame -> frame.calculateScore(point.getPoint()));
    }

    private List<Frame> initFrames() {
        List<Frame> baseframes = new ArrayList<>();
        for (int i = 1; i <= TOTAL_NORMAL_FRAME; i++) {
            baseframes.add(new NormalFrame());
        }
        baseframes.add(new FinalFrame());
        return baseframes;
    }
}
