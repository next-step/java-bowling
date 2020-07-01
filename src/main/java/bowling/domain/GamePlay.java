package bowling.domain;

import bowling.strategy.BowlingStrategy;
import bowling.view.OutputView;

import java.util.List;

public class GamePlay {

    private static final int MAX_FRAME_COUNT = 10;
    private static final int MAX_FRAME_INDEX = MAX_FRAME_COUNT - 1;
    private Player player;
    private Frames frames = new Frames();
    private Scores scores = new Scores();

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

    public static GamePlay play(Player player, BowlingStrategy bowlingStrategy) {
        return new GamePlay(player, bowlingStrategy);
    }

    private void drawAndShowResult(Player player, BowlingStrategy bowlingStrategy, int index) {
        Frame frame = drawBowl(bowlingStrategy, index);
        this.scores.add(Scores.createScore(frame.state, frame));
        this.frames.add(frame);
        OutputView.output(player, this);
    }

    private void drawAndShowResult(Player player, BowlingStrategy bowlingStrategy, Frame lastFrame) {
        lastFrame = (FinalFrame) drawBowl(bowlingStrategy, lastFrame);
        this.frames.replaceFinalFrame(lastFrame);
        this.scores.add(Scores.createScore(lastFrame.state, lastFrame));
        OutputView.output(player, this);
    }

    public Frame drawBowl(BowlingStrategy bowlingStrategy, int index) {
        Pin firstPin = bowlingStrategy.drawBowl(new Pin(), index);
        Pin secondPin = bowlingStrategy.drawBowl(firstPin, index);
        return Frame.of(new Pins(firstPin, secondPin));
    }

    public Frame drawBowl(BowlingStrategy bowlingStrategy, Frame lastFrame) {
        if (lastFrame.state == State.STRIKE) {
            lastFrame.setSecondPin(bowlingStrategy.drawBowl(new Pin(), MAX_FRAME_INDEX));
        }
        lastFrame.setThirdPin(bowlingStrategy.drawBowl(new Pin(), MAX_FRAME_INDEX));
        return Frame.of(lastFrame);
    }

    public List<String> showFrameResult() {
        return frames.showGameResult();
    }

    public List<String> showFrameScore() {
        return scores.showGameScore();
    }

    public List<String> showFrameSumScore() {
        return scores.showGameSumScore();
    }

}
