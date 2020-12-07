package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private Player player;
    private LinkedList<Frame> frames;

    private Frames(Player player, LinkedList<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Frames init(Player player) {
        LinkedList<Frame> firstFrames = new LinkedList<>();
        firstFrames.add(NormalFrame.first());

        return new Frames(player, firstFrames);
    }

    public static Frames of(Frames previousFrames, Frame frame) {
        LinkedList<Frame> nowFrames = new LinkedList<>(previousFrames.frames);
        nowFrames.add(frame);

        return new Frames(previousFrames.player, nowFrames);
    }

    public  boolean isNotEnd() {
        return frames.size() <= Frame.LAST_FRAME;
    }

    public List<Score> getResult(int frameNumber) {
        return frames.stream()
                .filter(frame -> frame.getBy(frameNumber))
                .findFirst()
                .map(Frame::getResult)
                .orElse(Collections.emptyList());
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getLastNumber() {
        return frames.size();
    }

    public void bowl(Score score) {
        Frame frame = frames.getLast();
        frame.bowl(score);

        if (!frame.canBowl()) {
            frame = frame.next();
            frames.add(frame);
        }
    }

    public int getScore() {
        return 0;
    }
}
