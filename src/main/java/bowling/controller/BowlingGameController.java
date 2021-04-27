package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;

public class BowlingGameController {
    public static final int START_FRAME_NO = 1;
    public static final int MAX_FRAME_NO = 10;

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGameController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        Player player = new Player(inputView.player());
        resultView.initBoard(player);
        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> frame(frameNo, player));
    }

    private void frame(int frameNo, Player player) {
        while (player.isFrameContinue(frameNo)) {
            Frames frames = player.pitch(frameNo, inputView.pitch(frameNo));
            resultView.printBoard(player, frames);
        }
    }
}
