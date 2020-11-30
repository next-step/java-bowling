package step2.controller;

import step2.domain.BowlingGame;
import step2.domain.Frame;
import step2.domain.Frames;
import step2.domain.Player;
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

        PlayerDTO playerDTO = new PlayerDTO(new Player(playerName), frames);

        pitches(playerDTO);

    }

    private void pitches(PlayerDTO dto) {
        Frames frames = dto.getFrames();
        try{
            while (!frames.isFinished()) {
                Frame currentFrame = frames.getCurrentFrame();
                int pitchesCount = view.getPitchesCount(currentFrame);

                List<String> marks = BowlingGame.pitches(frames, pitchesCount);

                view.drawFrame(dto.getPlayer(), marks);
            }
        }catch(InvalidPitchesException | IllegalArgumentException error){
            System.out.println(error.getMessage());
            pitches(dto);
        }
    }
}
