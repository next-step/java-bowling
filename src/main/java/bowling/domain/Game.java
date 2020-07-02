package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Frame> frames;
    private final Player player;

    public Game(Player player) {
        this.frames = startGame();
        this.player = player;
    }

    public List<Frame> startGame() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame(i));
        }
        frames.add(new FinalFrame());
        return frames;
    }

    public Frame getNextFrame() {
        return frames.stream()
                .filter(frame -> frame.canRoll())
                .findFirst()
                .orElse(null);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }
}
