package bowling.domain;

import bowling.strategy.BowlingStrategy;
import bowling.view.OutputView;

import java.util.List;

public class GamePlay {

    private static final int MAX_FRAME_COUNT = 10;
    private static final int FIRST_FRAME_INDEX_ZERO = 0;
    private static final int MAX_FRAME_INDEX = MAX_FRAME_COUNT - 1;
    private Player player;
    private Frames frames = new Frames();

    private GamePlay(Player player, BowlingStrategy bowlingStrategy) {
        this.player = player;
        for (int index = 0; index < MAX_FRAME_COUNT; index++) {
            drawAndShowResult(player, bowlingStrategy, index);
        }
        // last draw check
        Frame frame = this.frames.checkLastFrame();
        if (frame.state != State.MISS && frame.state != State.GURTER) {
            drawAndShowResult(player, bowlingStrategy, frame);
        }
    }

    private void drawAndShowResult(Player player, BowlingStrategy bowlingStrategy, int index) {
        Frame frame = drawBowl(bowlingStrategy, index);
        frame.createScore();
        if (index != FIRST_FRAME_INDEX_ZERO) {
            frames.setNextScore(frame);
        }
        this.frames.add(frame);
        OutputView.output(player, this);
    }

    private void drawAndShowResult(Player player, BowlingStrategy bowlingStrategy, Frame lastFrame) {
        lastFrame = drawBowl(bowlingStrategy, lastFrame);
        lastFrame.createScore();
        frames.replaceFinalFrame(lastFrame);
        OutputView.output(player, this);
    }

    public static GamePlay play(Player player, BowlingStrategy bowlingStrategy) {
        return new GamePlay(player, bowlingStrategy);
    }

    public Frame drawBowl(BowlingStrategy bowlingStrategy, int index) {
        Pin firstPin = bowlingStrategy.drawBowl(new Pin(), index);
        Pin secondPin = bowlingStrategy.drawBowl(firstPin, index);
        return Frame.of(firstPin, secondPin);
    }

    public Frame drawBowl(BowlingStrategy bowlingStrategy, Frame lastFrame) {
        if (lastFrame.state == State.STRIKE) {
            lastFrame.secondPin = bowlingStrategy.drawBowl(new Pin(), MAX_FRAME_INDEX);
        }
        Pin thirdPin = bowlingStrategy.drawBowl(new Pin(), MAX_FRAME_INDEX);
        return Frame.of(thirdPin, lastFrame);
    }

    public List<String> showFrameResult() {
        return frames.showGameResult();
    }

    public List<String> showFrameScore() {
        return frames.showGameScore();
    }

    public List<String> showFrameSumScore() {
        return frames.showGameSumScore();
    }

}
