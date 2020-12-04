package step3.controller;

import step3.domain.BowlingGame;
import step3.domain.Frame;
import step3.domain.Frames;
import step3.domain.*;
import step3.exception.InvalidPitchesException;
import step3.view.ConsoleViewImpl;
import step3.view.InputView;
import step3.view.ResultView;
import step3.view.View;

import java.util.List;

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

            List<String> marks = BowlingGame.pitches(frames, pitchesCount);

            view.drawFrame(player, marks);
        }
    }
}
