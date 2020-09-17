package bowling.domain;

import bowling.score.Score;

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
        return new Frames(player, new LinkedList<>());
    }

    public static Frames of(Frames previousFrames, Frame frame) {
        LinkedList<Frame> nowFrames = new LinkedList<>(previousFrames.frames);
        nowFrames.add(frame);

        return new Frames(previousFrames.player, nowFrames);
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
}
