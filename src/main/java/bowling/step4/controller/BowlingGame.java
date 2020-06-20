package bowling.step4.controller;

import bowling.step4.domain.*;
import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.domain.scores.Scores;
import bowling.step4.view.InputView;
import bowling.step4.view.ResultView;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private static final InputView inputView = InputView.getInstance();
    private static final ResultView resultView = ResultView.getInstance();

    private final PlayersFrames playersFrames;

    public BowlingGame(PlayersFrames playersFrames) {
        this.playersFrames = playersFrames;
    }

    public void normalFrameView() {
        playersFrames.stream()
                     .map(PlayerFrameDTO::of)
                     .peek(this::frameView)
                     .filter(playerFrameDTO -> !ScoresType.STRIKE.of(playerFrameDTO.getFrameScores()))
                     .collect(toList())
                     .forEach(this::frameView);
    }

    public void finalFrameView() {
        playersFrames.stream()
                     .map(PlayerFrameDTO::of)
                     .peek(this::frameView)
                     .collect(toList()).stream()
                     .peek(this::frameView)
                     .filter(playerFrameDTO -> ScoresType.BONUS.of(playerFrameDTO.getFrameScores()))
                     .forEach(this::frameView);
    }

    private void frameView(PlayerFrameDTO playerFrameDTO) {
        Scores scores = playerFrameDTO.getFrameScores();
        Score nextScore = inputView.inputScore(playerFrameDTO.playerName());
        playerFrameDTO.getFrame()
                   .createNextFrameOfScores(scores.nextInit(nextScore));
        resultView.printFrames(playersFrames);
    }


    public static void main(String[] args) {
        PlayersFrames playersFrames = PlayerCount
                                        .of(inputView.inputPlayerCount())
                                        .ofPlayersFrames(i -> PlayerFrames.of(inputView.inputName(i), NormalFrame.start()));

        BowlingGame game = new BowlingGame(playersFrames);
        resultView.printFrames(playersFrames);

        while (!playersFrames.isLast()) {
            game.normalFrameView();
        }
        game.finalFrameView();
    }
}
