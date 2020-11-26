package step2.controller;

import step2.domain.BowlingGame;
import step2.domain.Frames;
import step2.domain.GameHistories;
import step2.domain.Player;
import step2.domain.dto.PlayerDTO;
import step2.strategy.BowlingPitchesStrategy;
import step2.view.ConsoleViewImpl;
import step2.view.InputView;
import step2.view.ResultView;
import step2.view.View;

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

        GameHistories histories = BowlingGame.start(playerDTO, new BowlingPitchesStrategy());

        view.drawFrames(histories);

    }
}
