package domain;

import domain.frame.Frame;
import domain.frame.GameOverException;
import domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BowlingGame {

    private Player player;
    private List<Frame> frames;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = new ArrayList<>(Arrays.asList(NormalFrame.initFrame()));
    }

    public static BowlingGame from(Player player) {
        return new BowlingGame(player);
    }

    public void play(Pins fallenPins) {
        if (isGameOver()) {
            throw new GameOverException();
        }
        Frame bowledFrame = currentFrame().fillFrame(fallenPins);

        if (bowledFrame.getIndex().isSameIndex(currentFrame().getIndex())) {
            frames.set(lastFrameIndex(), bowledFrame);
        }
        if (!bowledFrame.getIndex().isSameIndex(currentFrame().getIndex())) {
            frames.add(bowledFrame);
        }
    }

    public Frame currentFrame() {
        return frames.get(lastFrameIndex());
    }

    public boolean isGameOver() {
        return frames.get(lastFrameIndex()).isGameOver();
    }

    private int lastFrameIndex() {
        return frames.size() - 1;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
