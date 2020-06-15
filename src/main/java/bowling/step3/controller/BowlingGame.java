package bowling.step3.controller;

import bowling.step3.domain.*;
import bowling.step3.domain.frame.*;
import bowling.step3.domain.scores.*;
import bowling.step3.view.*;

import java.util.Optional;

public class BowlingGame {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private final PlayerFrames playerFrames;

    public BowlingGame(PlayerFrames playerFrames) {
        this.playerFrames = playerFrames;
    }

    private void frameView(Frame frame) {
        Scores scores = frame.getScores();
        frame.createNextFrame(scores.nextInit(inputView.inputScore(frame.getValue())));
        resultView.printFrames(playerFrames);
    }

    public void normalFrameView(Frame nowFrame) {
        frameView(nowFrame);
        if (!nowFrame.getScores().isType(ScoreType.STRIKE)) {
            frameView(nowFrame);
        }
    }

    public void finalFrameView(Frame frame) {
        frameView(frame);
        frameView(frame);
        Scores scores = frame.getScores();
        if (scores.isType(ScoreType.STRIKE) || scores.isType(ScoreType.SPARED)) {
            frameView(frame);
        }
    }

    public static void main(String[] args) {
        Player player = inputView.inputName();
        Frame temp = NormalFrame.of(1, NormalScores.init(), null);
        PlayerFrames playerFrames = PlayerFrames.of(player, temp);

        BowlingGame game = new BowlingGame(playerFrames);

        resultView.printFrames(playerFrames);
        while (temp instanceof NormalFrame) {
            game.normalFrameView(temp);
            temp = Optional.ofNullable(temp.getNextFrame())
                           .orElse(temp);
        }
        game.finalFrameView(temp);
    }
}
