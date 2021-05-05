package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Players;
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
        Players players = inputView.players();

        resultView.printBoard(players);

        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> players.forEach(player -> frame(frameNo, player, players)));
    }

    private void frame(int frameNo, Player player, Players players) {
        while (player.isFrameContinue(frameNo)) {
            player.pitch(frameNo, inputView.pitch(player, frameNo));
            resultView.printBoard(players);
        }
    }
}
