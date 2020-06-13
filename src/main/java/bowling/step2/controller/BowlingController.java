package bowling.step2.controller;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;
import bowling.step2.view.*;
import bowling.step2.domain.*;

public class BowlingController {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private BowlingController () {}

    public static Frame frameView (Frame frame, Player player) {
        Score score = inputView.inputScore(frame.getValue());
        Scores scores = frame.getScores().nextInit(score);
        Frame nextFrame = frame.createNextFrame(scores);
        resultView.printFrames(PlayerFrames.ofFrame(player, nextFrame));
        return nextFrame;
    }

    public static Frame normalFrameView (Frame frame, Player player) {
        Frame nextFrame = frameView(frame, player);
        if (!nextFrame.getScores().isStrike()) {
            nextFrame = frameView(nextFrame, player);
        }
        return nextFrame;
    }

    public static void finalFrameView (Frame frame, Player player) {
        Frame nextFrame = frameView(frame, player);
        nextFrame = frameView(nextFrame, player);
        Scores scores = nextFrame.getScores();
        if (scores.isStrike() || scores.isSpared()) {
            frameView(nextFrame, player);
        }
    }

    public static void main(String[] args) {
        Player player = Player.valueOf("hji");

        resultView.printFrames(PlayerFrames.init(player));
        Frame temp = NormalFrame.of(1, NormalScores.init(), null);
        while (temp instanceof NormalFrame) {
            temp = normalFrameView(temp, player);
        }
        finalFrameView(temp, player);
    }
}
