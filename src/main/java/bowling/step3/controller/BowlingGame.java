package bowling.step3.controller;

import bowling.step3.domain.*;
import bowling.step3.domain.frame.*;
import bowling.step3.domain.scores.*;
import bowling.step3.view.*;

public class BowlingGame {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private BowlingGame() { }

    public static void frameView(Frame frame, PlayerFrames playerFrames) {
        frame.createNextFrame(
            frame.getScores()
                 .nextInit(inputView.inputScore(frame.getValue())));
        resultView.printFrames(playerFrames);
    }

    public static void normalFrameView(Frame frame, PlayerFrames playerFrames) {
        frameView(frame, playerFrames);
        if (frame.getScores().isType(ScoreType.STRIKE)) {
            frameView(frame, playerFrames);
        }
    }

    public static void finalFrameView(Frame frame, PlayerFrames playerFrames) {
        frameView(frame, playerFrames);
        frameView(frame, playerFrames);
        Scores scores = frame.getScores();
        if (scores.isType(ScoreType.STRIKE) || scores.isType(ScoreType.SPARED)) {
            frameView(frame, playerFrames);
        }
    }

    public static void main(String[] args) {
        Player player = inputView.inputName();
        Frame temp = NormalFrame.of(1, NormalScores.init(), null);
        PlayerFrames playerFrames = PlayerFrames.of(player, temp);
        resultView.printFrames(playerFrames);
        while (temp instanceof NormalFrame) {
            normalFrameView(temp, playerFrames);
            temp = temp.getNextFrame();
        }
        finalFrameView(temp, playerFrames);
    }
}
