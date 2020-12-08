package step4.controller;

import step4.domain.BowlingGame;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.*;
import step4.exception.InvalidPitchesException;
import step4.view.ConsoleViewImpl;
import step4.view.InputView;
import step4.view.ResultView;
import step4.view.View;

public class BowlingController {
    private final View view;

    public BowlingController(ResultView resultView, InputView inputView) {
        this.view = new ConsoleViewImpl(inputView, resultView);
    }

    public void gameStart() {
        String playerName = view.getPlayerName();
        Frames frames = BowlingGame.build();

        view.drawEmptyLine(playerName);

        pitches(new Player(playerName), frames);

    }

    private void pitches(Player player, Frames frames) {
        try {
            loopPitches(player, frames);
        } catch (InvalidPitchesException | IllegalArgumentException error) {
            System.out.println(error.getMessage());
            pitches(player, frames);
        }
    }

    private void loopPitches(Player player, Frames frames) {
        while (!frames.isFinished()) {
            Frame currentFrame = frames.getCurrentFrame();
            int pitchesCount = view.getPitchesCount(currentFrame);

            GameHistory gameHistory = BowlingGame.pitches(frames, pitchesCount);

            view.drawFrame(player, gameHistory);
        }
    }
}
