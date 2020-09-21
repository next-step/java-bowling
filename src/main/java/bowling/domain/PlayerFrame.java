package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.roll.Roll;
import bowling.domain.score.FrameScoreManager;

import java.util.function.ToIntFunction;

public class PlayerFrame {

    private final Frames frames = new Frames();
    private final FrameScoreManager scoreManager = new FrameScoreManager();
    private final Pins pins = new Pins();
    private final Player player;

    public PlayerFrame(Player player) {
        this.player = player;
    }

    public boolean canRoll() {
        return !frames.cannotRollMore(pins);
    }

    public void roll(ToIntFunction<Integer> playerRollFunction, Runnable runnable) {
        doRoll(playerRollFunction);
        if (!canRoll()) {
            frames.endCurrentFrame();
            scoreManager.registerEndFrame(frames.getCurrFrameScore());
        }
        runnable.run();
    }

    public boolean isComplete() {
        return frames.isComplete();
    }

    public FrameRecordIterator getFrameRecordIterator() {
        return new FrameRecordIterator(player,
                frames.markingIterator(),
                scoreManager.scoreIterator());
    }

    public void nextFrame() {
        frames.nextFrame();
        if (pins.isNotAvailable()) {
            pins.reset();
        }
    }

    private void doRoll(ToIntFunction<Integer> playerRollFunction) {
        int countOfPins = playerRollFunction.applyAsInt(frames.getCurrentFrameNumber());
        Roll roll = pins.knockedDown(countOfPins);
        frames.addRoll(roll);
        scoreManager.notify(roll);
    }
}
