package bowling.step4.controller;

import bowling.step4.domain.*;
import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.domain.scores.Scores;
import bowling.step4.view.InputView;
import bowling.step4.view.ResultView;

import java.util.Optional;
import java.util.function.Consumer;

public class BowlingGame {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private final PlayersFrames playersFrames;

    public BowlingGame(PlayersFrames playersFrames) {
        this.playersFrames = playersFrames;
    }

    private Consumer<Frame> selectFrameView(Frame frame) {
        if (frame instanceof NormalFrame) {
            return this::normalFrameView;
        }
        return this::finalFrameView;
    }

    public void normalFrameView(Frame nowFrame) {
        frameView(nowFrame);
        if (!ScoresType.STRIKE.of(nowFrame.getScores())) {
            frameView(nowFrame);
        }
    }

    public void finalFrameView(Frame frame) {
        frameView(frame);
        frameView(frame);
        Scores scores = frame.getScores();
        if (ScoresType.STRIKE.of(scores) || ScoresType.SPARED.of(scores)) {
            frameView(frame);
        }
    }

    private void frameView(Frame frame) {
        Scores scores = frame.getScores();
        frame.createNextFrameOfScores(scores.nextInit(inputView.inputScore(frame.getValue())));
        resultView.printFrames(playersFrames);
    }


    public static void main(String[] args) {
        PlayersFrames playersFrames = PlayerCount
                                        .of(inputView.inputPlayerCount())
                                        .ofPlayersFrames(i -> {
                                            Player player = inputView.inputName(i);
                                            return PlayerFrames.of(player, NormalFrame.start());
                                        });

        BowlingGame game = new BowlingGame(playersFrames);
        while (!playersFrames.isFull()) {
            playersFrames.stream()
                         .map(PlayerFrames::getLastFrame)
                         .forEach(frame -> game.selectFrameView(frame).accept(frame));
        }
    }
}
