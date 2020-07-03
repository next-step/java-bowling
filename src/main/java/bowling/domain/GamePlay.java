package bowling.domain;

import bowling.strategy.BowlingStrategy;
import bowling.view.OutputView;

import java.util.List;

public class GamePlay {

    private static final int MAX_FRAME_COUNT = 10;
    private Player player;
    private Frames frames = new Frames();
    private Scores scores = new Scores();

    private GamePlay(Player player, BowlingStrategy bowlingStrategy) {
        this.player = player;
        for (int index = 0; index < MAX_FRAME_COUNT - 1; index++) {
            drawAndShowResult(player, bowlingStrategy, Frame.of(), index);
        }
        // final Frame
        drawAndShowResult(player, bowlingStrategy, Frame.ofFinal(), MAX_FRAME_COUNT - 1);
    }

    public static GamePlay play(Player player, BowlingStrategy bowlingStrategy) {
        return new GamePlay(player, bowlingStrategy);
    }

    private void drawAndShowResult(Player player, BowlingStrategy bowlingStrategy, Frame frame, int index) {
        frame.drawBowl(bowlingStrategy, index);
        this.frames.add(frame);
        this.scores.add(Scores.createScore(frame));
        OutputView.output(player, this);
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
