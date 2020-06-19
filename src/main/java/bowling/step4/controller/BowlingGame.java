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

    public void selectFrameView() {
        if (playersFrames.isLast()) {
            finalFrameView();
        }
        normalFrameView();
    }

    private void normalFrameView() {
        playersFrames.stream()
                     .map(PlayerFrame::of)
                     .peek(this::frameView)
                     .filter(playerFrame -> !ScoresType.STRIKE.of(playerFrame.getFrameScores()))
                     .forEach(this::frameView);
    }

    private void finalFrameView() {
        playersFrames.stream()
                     .map(PlayerFrame::of)
                     .peek(this::frameView)
                     .peek(this::frameView)
                     .filter(playerFrame -> ScoresType.BONUS.of(playerFrame.getFrameScores()))
                     .forEach(this::frameView);
    }

    private void frameView(PlayerFrame playerFrame) {
        Scores scores = playerFrame.getFrameScores();
        Score nextScore = inputView.inputScore(playerFrame.playerName());
        playerFrame.getFrame()
                   .createNextFrameOfScores(scores.nextInit(nextScore));
        resultView.printFrames(playersFrames);
    }


    public static void main(String[] args) {
        PlayersFrames playersFrames = PlayerCount
                                        .of(inputView.inputPlayerCount())
                                        .ofPlayersFrames(i -> PlayerFrames.of(inputView.inputName(i), NormalFrame.start()));

        BowlingGame game = new BowlingGame(playersFrames);
        while (!playersFrames.isFull()) {
            game.selectFrameView();
        }
    }
}
