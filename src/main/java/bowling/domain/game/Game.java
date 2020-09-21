package bowling.domain.game;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private static final int FINAL_FRAME = 9;
    private final LinkedList<Frame> frames;
    private final Player player;

    public Game(Player player) {
        this.frames = startGame();
        this.player = player;
    }

    private LinkedList<Frame> startGame() {
        LinkedList<Frame> frames = new LinkedList<>();
        for (int i = 1; i <= FINAL_FRAME; i++) {
            frames.add(new NormalFrame(i));
        }
        frames.add(new FinalFrame());
        return frames;
    }

    public Frame getNextFrame() {
        return frames.stream()
                .filter(Frame::canRoll)
                .findFirst()
                .orElseGet(() -> frames.get(FINAL_FRAME));
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }
}
