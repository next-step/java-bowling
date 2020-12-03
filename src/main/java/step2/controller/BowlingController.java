package step2.controller;

import step2.domain.BowlingGame;
import step2.domain.Frame;
import step2.domain.Frames;
import step2.domain.*;
import step2.domain.dto.PlayerDTO;
import step2.exception.InvalidPitchesException;
import step2.view.ConsoleViewImpl;
import step2.view.InputView;
import step2.view.ResultView;
import step2.view.View;

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
        try{
            loopPitches(player, frames);
        }catch(InvalidPitchesException | IllegalArgumentException error){
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
